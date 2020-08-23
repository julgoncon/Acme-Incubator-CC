/*
 * AdministratorCustomisationParameterUpdateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.customisationParameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCustomisationParameterUpdateService implements AbstractUpdateService<Administrator, CustomisationParameter> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorCustomisationParameterRepository repository;

	// AbstractListService<Anonymous, CustomisationParameter> interface ----------------


	@Override
	public boolean authorise(final Request<CustomisationParameter> request) {
		assert request != null;

		boolean result;
		Principal principal;

		principal = request.getPrincipal();
		result = principal.getActiveRole() == Administrator.class;

		return result;
	}

	@Override
	public void bind(final Request<CustomisationParameter> request, final CustomisationParameter entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CustomisationParameter> request, final CustomisationParameter entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamWords", "activitySector", "spamThreshold");
	}

	@Override
	public CustomisationParameter findOne(final Request<CustomisationParameter> request) {
		assert request != null;

		CustomisationParameter result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findById(id);

		return result;
	}

	@Override
	public void validate(final Request<CustomisationParameter> request, final CustomisationParameter entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<CustomisationParameter> request, final CustomisationParameter entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
