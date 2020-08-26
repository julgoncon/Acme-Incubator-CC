
package acme.features.bookkeeper.activity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activity.Activity;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class BookkeeperActivityListService implements AbstractListService<Bookkeeper, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	BookkeeperActivityRepository repository;

	// AbstractListService<Administrator, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		int investmentId = request.getModel().getInteger("investmentId");
		InvestmentRound inv = this.repository.findOneInvestmentRoundById(investmentId);
		Boolean isFinalMode;
		if (inv != null) {
			isFinalMode = inv.getFinalMode();
		} else {
			isFinalMode = false;
		}
		return isFinalMode;
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "budget");

	}

	@Override
	public Collection<Activity> findMany(final Request<Activity> request) {
		assert request != null;

		int investmentId = request.getModel().getInteger("investmentId");
		Collection<Activity> result = this.repository.findManyByInvestmentRound(investmentId);

		return result;
	}

}
