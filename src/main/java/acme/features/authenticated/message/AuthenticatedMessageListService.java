
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListService implements AbstractListService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedMessageRepository repository;

	// AbstractListService<Administrator, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		int forumId = request.getModel().getInteger("forumId");
		Collection<Authenticated> users = this.repository.findUsersByForumId(forumId);
		int userId = request.getPrincipal().getActiveRoleId();
		Boolean result = false;
		for (Authenticated auth : users) {
			if (auth.getId() == userId) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creation", "authenticated.userAccount.username");

	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {
		assert request != null;

		Collection<Message> result;
		int forumId = request.getModel().getInteger("forumId");
		result = this.repository.findManyByForumId(forumId);

		return result;
	}

}
