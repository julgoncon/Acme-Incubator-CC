
package acme.features.authenticated.forumUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forumUser.ForumUser;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedForumUserDeleteService implements AbstractDeleteService<Authenticated, ForumUser> {

	@Autowired
	AuthenticatedForumUserRepository repository;


	@Override
	public boolean authorise(final Request<ForumUser> request) {
		assert request != null;

		boolean res;
		Principal principal;
		int idPrincipal;
		int idCreator;
		int idForum;
		int idForumUser = request.getModel().getInteger("id");
		principal = request.getPrincipal();

		idPrincipal = principal.getActiveRoleId();
		idForum = this.repository.findIdForumByForumUser(idForumUser);
		idCreator = this.repository.findIdCreatorUser(idForum);

		res = idPrincipal == idCreator;

		return res;
	}

	@Override
	public void bind(final Request<ForumUser> request, final ForumUser entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<ForumUser> request, final ForumUser entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "authenticated.userAccount.username");
	}

	@Override
	public ForumUser findOne(final Request<ForumUser> request) {
		assert request != null;

		ForumUser result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findForumUserById(id);

		return result;
	}

	@Override
	public void validate(final Request<ForumUser> request, final ForumUser entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//El creador no se puede borrar de un hilo

		int forumId = entity.getForum().getId();
		int idCreator = this.repository.findIdCreatorUser(forumId);
		int idUser = entity.getAuthenticated().getId();

		boolean assertCreator = idUser == idCreator;

		errors.state(request, !assertCreator, "authenticated.userAccount.username", "authenticated.forumUser.error.deleteCreator");
	}

	@Override
	public void delete(final Request<ForumUser> request, final ForumUser entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
