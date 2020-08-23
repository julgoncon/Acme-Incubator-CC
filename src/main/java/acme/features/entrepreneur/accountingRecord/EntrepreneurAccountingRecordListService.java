
package acme.features.entrepreneur.accountingRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneurAccountingRecordListService implements AbstractListService<Entrepreneur, AccountingRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurAccountingRecordRepository repository;

	// AbstractListService<Administrator, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		int principalId = request.getPrincipal().getAccountId();
		int investmentId = request.getModel().getInteger("investmentId");
		InvestmentRound inv = this.repository.findOneInvestmentRoundById(investmentId);
		Boolean isMine = inv.getEntrepreneur().getUserAccount().getId() == principalId;

		return isMine;
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creation");

	}

	@Override
	public Collection<AccountingRecord> findMany(final Request<AccountingRecord> request) {
		assert request != null;

		int investmentId = request.getModel().getInteger("investmentId");
		Collection<AccountingRecord> result = this.repository.findManyByInvestmentRound(investmentId);

		return result;
	}

}
