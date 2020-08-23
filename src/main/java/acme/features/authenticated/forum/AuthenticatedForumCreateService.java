
package acme.features.authenticated.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forum.Forum;
import acme.entities.forumUser.ForumUser;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedForumCreateService implements AbstractCreateService<Authenticated, Forum> {

	@Autowired
	AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title");

	}

	@Override
	public Forum instantiate(final Request<Forum> request) {
		Forum res;
		res = new Forum();
		return res;
	}

	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Forum> request, final Forum entity) {

		this.repository.save(entity);

		int idForum = entity.getId();
		int idUser = request.getPrincipal().getActiveRoleId();

		this.createidForumUser(idForum, idUser);
	}

	// Ancillary functions ----------------

	public void createidForumUser(final int idForum, final int idUser) {
		ForumUser res;
		Forum forum;
		Authenticated user;

		forum = this.repository.findOneById(idForum);
		user = this.repository.findAuthenticatedById(idUser);

		res = new ForumUser();

		res.setForum(forum);
		res.setAuthenticated(user);
		res.setCreator(true);

		this.repository.save(res);
	}

}
