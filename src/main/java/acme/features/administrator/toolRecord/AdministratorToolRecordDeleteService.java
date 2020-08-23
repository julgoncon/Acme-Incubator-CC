
package acme.features.administrator.toolRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolRecord.ToolRecord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorToolRecordDeleteService implements AbstractDeleteService<Administrator, ToolRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorToolRecordRepository repository;
	// AbstractCreateService<Administrator, InvestorRecord> ---------------------------


	@Override
	public boolean authorise(final Request<ToolRecord> request) {
		assert request != null;

		boolean result;
		Principal principal;

		principal = request.getPrincipal();
		result = principal.getActiveRole() == Administrator.class;

		return result;
	}

	@Override
	public void bind(final Request<ToolRecord> request, final ToolRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<ToolRecord> request, final ToolRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "activitySector", "inventorName", "description", "website", "email", "openSource", "stars");
	}

	@Override
	public ToolRecord findOne(final Request<ToolRecord> request) {
		assert request != null;

		ToolRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<ToolRecord> request, final ToolRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<ToolRecord> request, final ToolRecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
