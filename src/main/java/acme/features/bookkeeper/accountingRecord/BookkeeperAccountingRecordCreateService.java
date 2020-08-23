
package acme.features.bookkeeper.accountingRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class BookkeeperAccountingRecordCreateService implements AbstractCreateService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository repository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;
		int investmentId = request.getModel().getInteger("investmentId");
		InvestmentRound inv = this.repository.findOneInvestmentRoundById(investmentId);
		Boolean isFinalMode = inv.getFinalMode();
		return isFinalMode;
	}

	@Override
	public void bind(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		int investmentId = request.getModel().getInteger("investmentId");
		request.unbind(entity, model, "title", "body", "draft");
		model.setAttribute("investmentId", investmentId);

	}

	@Override
	public AccountingRecord instantiate(final Request<AccountingRecord> request) {
		AccountingRecord res;
		res = new AccountingRecord();
		int investmentId = request.getModel().getInteger("investmentId");
		InvestmentRound inv = this.repository.findOneInvestmentRoundById(investmentId);

		Principal principal;
		int userId;
		Bookkeeper bookkeeper;

		principal = request.getPrincipal();
		userId = principal.getActiveRoleId();

		bookkeeper = this.repository.findOneBookkeeperById(userId);
		res.setBookkeeper(bookkeeper);
		res.setInvestmentRound(inv);
		res.setDraft(true);
		return res;
	}

	@Override
	public void validate(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AccountingRecord> request, final AccountingRecord entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		entity.setCreation(moment);
		this.repository.save(entity);

	}

	// Ancillary functions ----------------

}
