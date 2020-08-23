
package acme.features.entrepreneur.activity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activity.Activity;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurActivityCreateService implements AbstractCreateService<Entrepreneur, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int investmentId = request.getModel().getInteger("investmentId");
		request.unbind(entity, model, "title", "startMoment", "endMoment", "budget");
		model.setAttribute("investmentId", investmentId);
	}

	@Override
	public Activity instantiate(final Request<Activity> request) {
		Activity result;

		int id = request.getModel().getInteger("investmentId");
		InvestmentRound invest = this.repository.findOneInvestmentRoundById(id);
		result = new Activity();
		result.setInvestmentRound(invest);

		return result;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("budget")) {

			Boolean assertCurrency1 = entity.getBudget().getCurrency().contentEquals("€") || entity.getBudget().getCurrency().contentEquals("EUR");
			Boolean assertOver0 = !entity.getBudget().getAmount().equals(0.0);
			errors.state(request, assertCurrency1, "budget", "entrepreneur.activity.error.budget");
			errors.state(request, assertOver0, "budget", "entrepreneur.activity.error.budgetOver0");

		}

		if (!errors.hasErrors("startMoment") && !errors.hasErrors("endMoment")) {
			boolean assertStartMoment = entity.getEndMoment().after(entity.getStartMoment());
			boolean assertFuture = entity.getStartMoment().after(new Date(System.currentTimeMillis()));

			errors.state(request, assertFuture, "startMoment", "entrepreneur.activity.error.startMomentFuture");
			errors.state(request, assertStartMoment, "startMoment", "entrepreneur.activity.error.startMoment");
		}

	}

	@Override
	public void create(final Request<Activity> request, final Activity entity) {

		this.repository.save(entity);
	}

}
