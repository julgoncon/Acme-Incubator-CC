/*
 * AnonymousCompanyRecordCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenge.Challenge;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedChallengeShowService implements AbstractShowService<Authenticated, Challenge> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedChallengeRepository repository;

	// AbstractListService<Anonymous, CompanyRecord> interface ----------------


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;
		int challengeId = request.getModel().getInteger("id");
		Challenge challenge = this.repository.findOneActive(challengeId);
		Boolean isActive = challenge != null;
		return isActive;
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "description", "deadline", "rookieLevelReward", "averageLevelReward", "expertLevelReward", "rookieLevelGoal", "averageLevelGoal", "expertLevelGoal");
	}

	@Override
	public Challenge findOne(final Request<Challenge> request) {
		assert request != null;

		Challenge result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
