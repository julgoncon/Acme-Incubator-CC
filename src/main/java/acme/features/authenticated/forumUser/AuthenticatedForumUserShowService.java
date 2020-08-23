
package acme.features.authenticated.forumUser;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forumUser.ForumUser;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedForumUserShowService implements AbstractShowService<Authenticated, ForumUser> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedForumUserRepository repository;

	// AbstractListService<Authenticated, Job> interface ---------------


	@Override
	public boolean authorise(final Request<ForumUser> request) {
		assert request != null;

		boolean res = false;

		Principal principal;
		int idPrincipal;
		int forumId;
		int idForumUser = request.getModel().getInteger("id");
		principal = request.getPrincipal();

		idPrincipal = principal.getActiveRoleId();
		forumId = this.repository.findIdForumByForumUser(idForumUser);

		Collection<ForumUser> users = this.repository.findUsersByForum(forumId);

		for (ForumUser user : users) {
			if (user.getAuthenticated().getId() == idPrincipal) {
				res = true;
				break;
			}
		}

		return res;

	}

	@Override
	public void unbind(final Request<ForumUser> request, final ForumUser entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "authenticated.userAccount.username");

		Principal principal = request.getPrincipal();
		int idPrincipal = principal.getActiveRoleId();
		int idForumUser = request.getModel().getInteger("id");
		int forumId = this.repository.findIdForumByForumUser(idForumUser);
		int idCreator = this.repository.findIdCreatorUser(forumId);

		model.setAttribute("idPrincipal", idPrincipal);
		model.setAttribute("idCreator", idCreator);
	}

	@Override
	public ForumUser findOne(final Request<ForumUser> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		ForumUser res = this.repository.findForumUserById(id);

		return res;
	}

}
