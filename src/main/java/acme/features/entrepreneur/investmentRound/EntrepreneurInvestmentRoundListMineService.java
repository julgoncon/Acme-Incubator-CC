
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneurInvestmentRoundListMineService implements AbstractListService<Entrepreneur, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;

	// AbstractListService<Administrator, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "creation", "kindRound", "amountMoney");

	}

	@Override
	public Collection<InvestmentRound> findMany(final Request<InvestmentRound> request) {
		assert request != null;

		Principal principal;

		principal = request.getPrincipal();
		int entrepreneurId = principal.getActiveRoleId();
		Collection<InvestmentRound> result = this.repository.findManyByEntrepreneurId(entrepreneurId);

		return result;
	}

}
