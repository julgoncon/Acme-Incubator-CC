
package acme.features.bookkeeper.accountingRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class BookkeeperAccountingRecordUpdateService implements AbstractUpdateService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository repository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;
		int bookkeeperId = request.getPrincipal().getActiveRoleId();
		int accountingId = request.getModel().getInteger("id");
		AccountingRecord acc = this.repository.findOneById(accountingId);
		Boolean isMine = acc.getBookkeeper().getId() == bookkeeperId;
		Boolean valid = acc.getInvestmentRound().getFinalMode() == true && !acc.getDraft() || isMine;
		return valid;
	}

	@Override
	public AccountingRecord findOne(final Request<AccountingRecord> request) {
		assert request != null;

		AccountingRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void bind(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation");
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int bookkeeperId = request.getPrincipal().getActiveRoleId();
		int accountingId = request.getModel().getInteger("id");
		AccountingRecord acc = this.repository.findOneById(accountingId);
		Boolean isMine = acc.getBookkeeper().getId() == bookkeeperId;
		Boolean isDraft = acc.getDraft();
		Boolean canUpdate = isMine && isDraft;
		int investmentId = entity.getInvestmentRound().getId();
		request.unbind(entity, model, "title", "draft", "body");
		model.setAttribute("canUpdate", canUpdate);
		model.setAttribute("investmentId", investmentId);
	}

	@Override
	public void validate(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<AccountingRecord> request, final AccountingRecord entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
