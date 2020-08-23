
package acme.features.authenticated.bookkeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedBookkeeperRequestCreateService implements AbstractCreateService<Authenticated, BookkeeperRequest> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedBookkeeperRequestRepository repository;


	@Override
	public boolean authorise(final Request<BookkeeperRequest> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firmName", "responsibilityStatement");

	}

	@Override
	public BookkeeperRequest instantiate(final Request<BookkeeperRequest> request) {
		BookkeeperRequest res = new BookkeeperRequest();
		int principalId = request.getPrincipal().getAccountId();
		UserAccount authUA = this.repository.findAuthenticatedUAById(principalId);
		res.setUserAccount(authUA);
		res.setStatus(null);

		return res;
	}

	@Override
	public void validate(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors())

		{

			Boolean hasRequest = false;
			int principalId = request.getPrincipal().getAccountId();
			int nUA = this.repository.numberBookkeeperRequestsByUA(principalId);
			if (nUA > 0) {
				hasRequest = true;
			}
			errors.state(request, !hasRequest, "firmName", "authenticated.bookkeeperRequest.error.alreadyRequested");

		}
	}

	@Override
	public void create(final Request<BookkeeperRequest> request, final BookkeeperRequest entity) {
		this.repository.save(entity);
	}

}
