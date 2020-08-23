
package acme.features.administrator.overture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overture.Overture;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorOvertureDeleteService implements AbstractDeleteService<Administrator, Overture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorOvertureRepository repository;
	// AbstractCreateService<Administrator, InvestorRecord> ---------------------------


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;

		boolean result;
		Principal principal;

		principal = request.getPrincipal();
		result = principal.getActiveRole() == Administrator.class;

		return result;
	}

	@Override
	public void bind(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation");
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "paragraphs", "minMoney", "maxMoney", "email");
	}

	@Override
	public Overture findOne(final Request<Overture> request) {
		assert request != null;

		Overture result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Overture> request, final Overture entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
