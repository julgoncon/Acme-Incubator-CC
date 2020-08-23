
package acme.features.authenticated.forumUser;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forumUser.ForumUser;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedForumUserListService implements AbstractListService<Authenticated, ForumUser> {

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
		Collection<ForumUser> users;
		int forumId = request.getModel().getInteger("forumId");
		principal = request.getPrincipal();

		idPrincipal = principal.getActiveRoleId();
		users = this.repository.findUsersByForum(forumId);

		for (ForumUser iterator : users) {
			if (iterator.getAuthenticated().getId() == idPrincipal) {
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
	}

	@Override
	public Collection<ForumUser> findMany(final Request<ForumUser> request) {
		assert request != null;

		Collection<ForumUser> res;
		int forumId;

		forumId = request.getModel().getInteger("forumId");
		res = this.repository.findUsersByForum(forumId);

		return res;
	}

}
