
package acme.features.entrepreneur.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneurApplicationListMineService implements AbstractListService<Entrepreneur, Application> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "investmentRound.title", "investmentRound.ticker", "status", "investmentOffer", "creation");

	}

	@Override
	public Collection<Application> findMany(final Request<Application> request) {
		assert request != null;

		Principal principal;

		principal = request.getPrincipal();
		int entrepreneurId = principal.getActiveRoleId();
		Collection<Application> result = this.repository.findManyApplicationByEntrepreneurId(entrepreneurId);

		return result;
	}

}
