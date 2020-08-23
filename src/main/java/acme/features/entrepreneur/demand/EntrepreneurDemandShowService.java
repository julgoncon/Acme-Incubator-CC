
package acme.features.entrepreneur.demand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.demand.Demand;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurDemandShowService implements AbstractShowService<Entrepreneur, Demand> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneurDemandRepository repository;

	// AbstractListService<Authenticated, Job> interface ---------------


	@Override
	public boolean authorise(final Request<Demand> request) {
		assert request != null;

		boolean result;
		int demandId;
		InvestmentRound investmentRound;
		Entrepreneur entrepreneur;
		Principal principal;

		demandId = request.getModel().getInteger("id");
		Demand demand = this.repository.findById(demandId);
		investmentRound = demand.getInvestmentRound();
		entrepreneur = investmentRound.getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId() && demand != null;

		return result;
	}

	@Override
	public void unbind(final Request<Demand> request, final Demand entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "text");
		int demandId = request.getModel().getInteger("id");
		Demand demand = this.repository.findById(demandId);
		int investmentId = demand.getInvestmentRound().getId();
		model.setAttribute("investmentId", investmentId);

	}

	@Override
	public Demand findOne(final Request<Demand> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		Demand res = this.repository.findById(id);
		return res;
	}
}
