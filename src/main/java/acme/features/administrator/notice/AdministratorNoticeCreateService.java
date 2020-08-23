
package acme.features.administrator.notice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notice.Notice;
import acme.features.administrator.inquiry.AdministratorInquiryRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorNoticeCreateService implements AbstractCreateService<Administrator, Notice> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorInquiryRepository repository;
	// AbstractCreateService<Administrator, InvestorRecord> ---------------------------


	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation");
	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "deadline", "body", "links");

		model.setAttribute("accept", "false");

	}

	@Override
	public Notice instantiate(final Request<Notice> request) {
		Notice result;

		result = new Notice();

		return result;
	}

	@Override
	public void validate(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("deadline")) {
			boolean assertDeadline = entity.getDeadline() != null && entity.getDeadline().after(new Date(System.currentTimeMillis()));
			errors.state(request, assertDeadline, "deadline", "administrator.notice.error.deadline");
		}

		boolean isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "administrator.notice.error.must-accept");

	}

	@Override
	public void create(final Request<Notice> request, final Notice entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(moment);
		this.repository.save(entity);
	}

}
