/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.entrepreneur.accountingRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurAccountingRecordShowService implements AbstractShowService<Entrepreneur, AccountingRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurAccountingRecordRepository repository;

	// AbstractCreateService<Authenticated, Announcement> ---------------------------


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		int principalId = request.getPrincipal().getAccountId();
		int accountingId = request.getModel().getInteger("id");
		AccountingRecord acc = this.repository.findOneById(accountingId);
		Boolean isMine = acc.getInvestmentRound().getEntrepreneur().getUserAccount().getId() == principalId && !acc.getDraft();

		return isMine;
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creation", "draft", "body");
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

}
