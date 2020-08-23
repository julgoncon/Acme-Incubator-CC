
package acme.features.authenticated.forumUser;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forum.Forum;
import acme.entities.forumUser.ForumUser;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedForumUserCreateService implements AbstractCreateService<Authenticated, ForumUser> {

	@Autowired
	AuthenticatedForumUserRepository repository;


	@Override
	public boolean authorise(final Request<ForumUser> request) {
		assert request != null;

		boolean res;
		Principal principal;
		int idPrincipal;
		int idCreator;
		int idForum = request.getModel().getInteger("forumId");
		principal = request.getPrincipal();

		idPrincipal = principal.getActiveRoleId();
		idCreator = this.repository.findIdCreatorUser(idForum);

		res = idPrincipal == idCreator;

		return res;
	}

	@Override
	public void bind(final Request<ForumUser> request, final ForumUser entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int forumId = request.getModel().getInteger("forumId");
		String username = request.getModel().getString("authenticated.userAccount.username");

		Authenticated user = this.repository.findUserByUsername(username);
		Forum forum = this.repository.findForumById(forumId);

		entity.setAuthenticated(user);
		entity.setForum(forum);
		entity.setCreator(false);

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<ForumUser> request, final ForumUser entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int forumId = request.getModel().getInteger("forumId");

		request.unbind(entity, model, "authenticated.userAccount.username");

		model.setAttribute("forumId", forumId);

	}

	@Override
	public ForumUser instantiate(final Request<ForumUser> request) {
		assert request != null;

		ForumUser res;
		res = new ForumUser();

		return res;
	}

	@Override
	public void validate(final Request<ForumUser> request, final ForumUser entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean assertRepeated = false;
		boolean assertNotFoundUser = true;

		Authenticated user = entity.getAuthenticated();
		int forumId = request.getModel().getInteger("forumId");
		Collection<ForumUser> list = this.repository.findUsersByForum(forumId);

		for (ForumUser iterador : list) {
			if (iterador.getAuthenticated().getId() == user.getId()) {
				assertRepeated = true;
				break;
			}
		}

		Collection<Authenticated> usersSystem = this.repository.findAllUsers();

		if (!usersSystem.contains(user)) {
			assertNotFoundUser = false;
		}

		errors.state(request, !assertRepeated, "authenticated.userAccount.username", "authenticated.forumUser.error.repeated");
		errors.state(request, assertNotFoundUser, "authenticated.userAccount.username", "authenticated.forumUser.error.noUserFound");
	}

	@Override
	public void create(final Request<ForumUser> request, final ForumUser entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
