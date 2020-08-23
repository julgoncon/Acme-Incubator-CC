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

package acme.features.entrepreneur.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activity.Activity;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurActivityShowService implements AbstractShowService<Entrepreneur, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurActivityRepository repository;

	// AbstractCreateService<Authenticated, Announcement> ---------------------------


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		int principalId = request.getPrincipal().getAccountId();
		int activityId = request.getModel().getInteger("id");
		Activity acc = this.repository.findOneById(activityId);
		Boolean isMine;
		if (acc != null) {
			isMine = acc.getInvestmentRound().getEntrepreneur().getUserAccount().getId() == principalId;
		} else {
			isMine = false;
		}
		return isMine;
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		Boolean isFinalMode = entity.getInvestmentRound().getFinalMode();
		request.unbind(entity, model, "title", "startMoment", "endMoment", "budget");
		model.setAttribute("finalMode", isFinalMode);

	}

	@Override
	public Activity findOne(final Request<Activity> request) {
		assert request != null;

		Activity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
