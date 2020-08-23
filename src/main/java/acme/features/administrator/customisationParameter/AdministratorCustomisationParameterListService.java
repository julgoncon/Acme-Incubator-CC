/*
 * AdministratorCustomisationParameterListService.java
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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorCustomisationParameterListService implements AbstractListService<Administrator, CustomisationParameter> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorCustomisationParameterRepository repository;

	// AbstractListService<Anonymous, CustomisationParameter> interface ----------------


	@Override
	public boolean authorise(final Request<CustomisationParameter> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<CustomisationParameter> request, final CustomisationParameter entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamWords", "activitySector", "spamThreshold");
	}

	@Override
	public Collection<CustomisationParameter> findMany(final Request<CustomisationParameter> request) {
		assert request != null;

		Collection<CustomisationParameter> result;

		result = this.repository.findManyAll();

		return result;
	}

}
