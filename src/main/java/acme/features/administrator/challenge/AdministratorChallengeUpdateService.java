
package acme.features.administrator.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenge.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorChallengeUpdateService implements AbstractUpdateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

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
		result = this.repository.findOneChallengeId(id);
		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Currency debe ser euros
		if (!errors.hasErrors("rookieLevelReward")) {

			boolean assertCurrency2 = entity.getRookieLevelReward().getCurrency().contentEquals("€") || entity.getRookieLevelReward().getCurrency().contentEquals("EUR");
			errors.state(request, assertCurrency2, "rookieLevelReward", "administrator.challenge.error.rookieLevelReward");

		}
		if (!errors.hasErrors("averageLevelReward")) {

			boolean assertCurrency1 = entity.getAverageLevelReward().getCurrency().contentEquals("€") || entity.getAverageLevelReward().getCurrency().contentEquals("EUR");
			errors.state(request, assertCurrency1, "averageLevelReward", "administrator.challenge.error.averageLevelReward");

		}
		if (!errors.hasErrors("expertLevelReward")) {

			boolean assertCurrency = entity.getExpertLevelReward().getCurrency().contentEquals("€") || entity.getExpertLevelReward().getCurrency().contentEquals("EUR");
			errors.state(request, assertCurrency, "expertLevelReward", "administrator.challenge.error.expertLevelReward");

		}

		//bronzeReward < silverReward < goldReward
		if (!errors.hasErrors("expertLevelReward") && !errors.hasErrors("averageLevelReward") && !errors.hasErrors("rookieLevelReward")) {

			boolean assertAmount = entity.getRookieLevelReward().getAmount() < entity.getAverageLevelReward().getAmount() && entity.getRookieLevelReward().getAmount() < entity.getExpertLevelReward().getAmount();
			errors.state(request, assertAmount, "rookieLevelReward", "administrator.challenge.error.rookieAmount");

			boolean assertAmount2 = entity.getAverageLevelReward().getAmount() < entity.getExpertLevelReward().getAmount() && entity.getAverageLevelReward().getAmount() > entity.getRookieLevelReward().getAmount();
			errors.state(request, assertAmount2, "averageLevelReward", "administrator.challenge.error.averageAmount");

			boolean assertAmount3 = entity.getAverageLevelReward().getAmount() < entity.getExpertLevelReward().getAmount() && entity.getRookieLevelReward().getAmount() < entity.getExpertLevelReward().getAmount();
			errors.state(request, assertAmount3, "expertLevelReward", "administrator.challenge.error.expertAmount");

		}

	}

	@Override
	public void update(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
