/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.technologyRecord;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.technologyRecord.TechnologyRecord;
import acme.features.administrator.customisationParameter.AdministratorCustomisationParameterRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorTechnologyRecordShowService implements AbstractShowService<Administrator, TechnologyRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorTechnologyRecordRepository			repository;

	@Autowired
	AdministratorCustomisationParameterRepository	customisationParametersRepository;
	// AbstractCreateService<Authenticated, Announcement> ---------------------------


	@Override
	public boolean authorise(final Request<TechnologyRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<TechnologyRecord> request, final TechnologyRecord entity, final Model model) {
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
		String selected = entity.getActivitySector();
		model.setAttribute("selected", selected);

	}

	@Override
	public TechnologyRecord findOne(final Request<TechnologyRecord> request) {
		assert request != null;

		TechnologyRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
