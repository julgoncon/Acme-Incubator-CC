
package acme.features.administrator.inquiry;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiry.Inquiry;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorInquiryUpdateService implements AbstractUpdateService<Administrator, Inquiry> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorInquiryRepository repository;
	// AbstractCreateService<Administrator, InvestorRecord> ---------------------------


	@Override
	public boolean authorise(final Request<Inquiry> request) {
		assert request != null;

		boolean result;
		Principal principal;

		principal = request.getPrincipal();
		result = principal.getActiveRole() == Administrator.class;

		return result;
	}

	@Override
	public void bind(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation");
	}

	@Override
	public void unbind(final Request<Inquiry> request, final Inquiry entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "paragraphs", "minMoney", "maxMoney", "email");
	}

	@Override
	public Inquiry findOne(final Request<Inquiry> request) {
		assert request != null;

		Inquiry result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("deadline")) {
			boolean assertDeadline = entity.getDeadline() != null && entity.getDeadline().after(new Date(System.currentTimeMillis()));
			errors.state(request, assertDeadline, "deadline", "administrator.inquiry.error.deadline");
		}

		//Currency debe ser euros
		if (!errors.hasErrors("minMoney")) {

			boolean assertCurrency = entity.getMinMoney().getCurrency().contentEquals("€") || entity.getMinMoney().getCurrency().contentEquals("EUR");
			errors.state(request, assertCurrency, "minMoney", "administrator.inquiry.error.minMoney");

		}

		if (!errors.hasErrors("maxMoney")) {

			boolean assertCurrency1 = entity.getMaxMoney().getCurrency().contentEquals("€") || entity.getMaxMoney().getCurrency().contentEquals("EUR");
			errors.state(request, assertCurrency1, "maxMoney", "administrator.inquiry.error.maxMoney");

		}

		if (!errors.hasErrors("maxMoney") && !errors.hasErrors("minMoney")) {

			boolean assertMinSmaller = entity.getMaxMoney().getAmount() > entity.getMinMoney().getAmount();
			errors.state(request, assertMinSmaller, "maxMoney", "administrator.inquiry.error.minSmaller");

		}
	}

	@Override
	public void update(final Request<Inquiry> request, final Inquiry entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
