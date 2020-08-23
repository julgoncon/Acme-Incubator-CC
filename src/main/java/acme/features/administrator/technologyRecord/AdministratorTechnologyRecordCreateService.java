
package acme.features.administrator.technologyRecord;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.technologyRecord.TechnologyRecord;
import acme.features.administrator.customisationParameter.AdministratorCustomisationParameterRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorTechnologyRecordCreateService implements AbstractCreateService<Administrator, TechnologyRecord> {

	@Autowired
	AdministratorTechnologyRecordRepository			repository;

	@Autowired
	AdministratorCustomisationParameterRepository	customisationParametersRepository;


	@Override
	public boolean authorise(final Request<TechnologyRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<TechnologyRecord> request, final TechnologyRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		if (entity.getOpenSource() == null) {
			entity.setOpenSource(false);
		}

	}

	@Override
	public void unbind(final Request<TechnologyRecord> request, final TechnologyRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

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
	public TechnologyRecord instantiate(final Request<TechnologyRecord> request) {
		TechnologyRecord result;

		result = new TechnologyRecord();

		return result;
	}

	@Override
	public void validate(final Request<TechnologyRecord> request, final TechnologyRecord entity, final Errors errors) {
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
	public void create(final Request<TechnologyRecord> request, final TechnologyRecord entity) {

		this.repository.save(entity);
	}

}
