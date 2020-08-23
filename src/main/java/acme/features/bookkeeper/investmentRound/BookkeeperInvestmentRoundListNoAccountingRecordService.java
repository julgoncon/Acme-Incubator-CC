
package acme.features.bookkeeper.investmentRound;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class BookkeeperInvestmentRoundListNoAccountingRecordService implements AbstractListService<Bookkeeper, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	@Autowired
	BookkeeperInvestmentRoundRepository repository;

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
		int bookkeeperId = principal.getActiveRoleId();
		Collection<InvestmentRound> accountedByMe = this.repository.findManyAccountedByBookkeeperId(bookkeeperId);
		Collection<InvestmentRound> allInv = this.repository.findManyInvestmentRound();
		Collection<InvestmentRound> notAccounted = new ArrayList<>();
		notAccounted.addAll(allInv);
		for (InvestmentRound inv : allInv) {
			if (accountedByMe.contains(inv)) {
				notAccounted.remove(inv);
			}
		}

		return notAccounted;
	}

}
