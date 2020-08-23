
package acme.features.entrepreneur.demand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.demand.Demand;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurDemandUpdateService implements AbstractUpdateService<Entrepreneur, Demand> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurDemandRepository repository;

	// AbstractUpdateService<Employer, Duty> interface ----------------


	@Override
	public boolean authorise(final Request<Demand> request) {
		assert request != null;

		boolean result;
		int investmentId;
		InvestmentRound investmentRound;
		Entrepreneur entrepreneur;
		Principal principal;

		investmentId = request.getModel().getInteger("investmentId");
		investmentRound = this.repository.findInvestmentRoundById(investmentId);
		entrepreneur = investmentRound.getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Demand> request, final Demand entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Demand> request, final Demand entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "text");
		int investmentId = request.getModel().getInteger("investmentId");
		model.setAttribute("investmentId", investmentId);

	}

	@Override
	public Demand findOne(final Request<Demand> request) {
		assert request != null;

		Demand result;
		int id;

		id = request.getModel().getInteger("investmentId");
		result = this.repository.findDemandByInvestmentRoundId(id);
		return result;
	}

	@Override
	public void validate(final Request<Demand> request, final Demand entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<Demand> request, final Demand entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

	// Ancillary functions ----------------

}
