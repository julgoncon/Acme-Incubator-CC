
package acme.features.authenticated.forum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forum.Forum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedForumListInvolvedService implements AbstractListService<Authenticated, Forum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedForumRepository repository;

	// AbstractListService<Administrator, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "investmentRound.title");

	}

	@Override
	public Collection<Forum> findMany(final Request<Forum> request) {
		assert request != null;

		Collection<Forum> result;
		int principalId = request.getPrincipal().getActiveRoleId();
		result = this.repository.findManyInvolved(principalId);

		return result;
	}

}
