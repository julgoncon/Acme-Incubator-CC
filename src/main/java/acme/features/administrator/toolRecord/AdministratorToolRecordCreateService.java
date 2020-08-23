
package acme.features.administrator.toolRecord;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolRecord.ToolRecord;
import acme.features.administrator.customisationParameter.AdministratorCustomisationParameterRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorToolRecordCreateService implements AbstractCreateService<Administrator, ToolRecord> {

	@Autowired
	AdministratorToolRecordRepository				repository;

	@Autowired
	AdministratorCustomisationParameterRepository	customisationParametersRepository;


	@Override
	public boolean authorise(final Request<ToolRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<ToolRecord> request, final ToolRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		if (entity.getOpenSource() == null) {
			entity.setOpenSource(false);
		}
	}

	@Override
	public void unbind(final Request<ToolRecord> request, final ToolRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "activitySector", "inventorName", "description", "website", "email", "openSource", "stars");

		String as = this.customisationParametersRepository.findActivitySector();
		request.unbind(entity, model, "title", "activitySector", "inventorName", "description", "website", "email", "openSource", "stars");
		String[] aslist = as.split(",");
		List<String> activitySectors = new ArrayList<>();
		for (String element : aslist) {
			activitySectors.add(element);
		}
		model.setAttribute("activitySectors", activitySectors);

	}

	@Override
	public ToolRecord instantiate(final Request<ToolRecord> request) {
		ToolRecord result;

		result = new ToolRecord();

		return result;
	}

	@Override
	public void validate(final Request<ToolRecord> request, final ToolRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("activitySector")) {
			String cp = this.customisationParametersRepository.findActivitySector();
			String[] listActSect = cp.split(",");
			int size = listActSect.length;
			Boolean isAvaible = false;
			for (int i = 0; i < size; i++) {
				if (listActSect[i].equals(entity.getActivitySector())) {
					isAvaible = true;
				}
				;
			}

			errors.state(request, isAvaible, "activitySector", "administrator.toolRecord.error.activitySector");
		}

	}
	@Override
	public void create(final Request<ToolRecord> request, final ToolRecord entity) {

		this.repository.save(entity);
	}

}
