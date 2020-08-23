
package acme.features.administrator.bookkeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorBookkeeperRequestAcceptService implements AbstractUpdateService<Administrator, BookkeeperRequest> {

	@Autowired
	AdministratorBookkeeperRequestRepository	repository;

	@Autowired
	AdministratorBookkeeperRepository			bookkeeperRepository;


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
	public BookkeeperRequest findOne(final Request<BookkeeperRequest> request) {
		assert request != null;

		BookkeeperRequest res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.repository.findOneById(id);

		return res;
	}

	@Override
	public void validate(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<BookkeeperRequest> request, final BookkeeperRequest entity) {
		assert request != null;
		assert entity != null;
		Bookkeeper bookkeeper = new Bookkeeper();
		bookkeeper.setFirmName(entity.getFirmName());
		bookkeeper.setResponsibilityStatement(entity.getResponsibilityStatement());
		UserAccount ua = entity.getUserAccount();
		bookkeeper.setUserAccount(ua);
		entity.setStatus(true);
		this.bookkeeperRepository.save(bookkeeper);
		this.repository.save(entity);
	}

	@Override
	public void unbind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Model model) {
		// TODO Auto-generated method stub

	}

}
