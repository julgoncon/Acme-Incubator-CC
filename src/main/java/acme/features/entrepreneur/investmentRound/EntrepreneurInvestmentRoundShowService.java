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

package acme.features.entrepreneur.investmentRound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurInvestmentRoundShowService implements AbstractShowService<Entrepreneur, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;

	// AbstractCreateService<Authenticated, Announcement> ---------------------------


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		boolean result;
		Entrepreneur entrepreneur;
		Principal principal;
		InvestmentRound investmentRound;
		int investmentRoundId;

		investmentRoundId = request.getModel().getInteger("id");
		investmentRound = this.repository.findOneById(investmentRoundId);
		entrepreneur = investmentRound.getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int investmentId = request.getModel().getInteger("id");
		int activities = this.repository.findActivitiesByInvestmentRound(investmentId).size();
		Integer applications = this.repository.countApplicationsByInvestmentRoundId(investmentId);
		int accountingRecords = this.repository.findAccountingRecordsByInvestmentRound(investmentId).size();
		request.unbind(entity, model, "ticker", "creation", "kindRound", "title", "finalMode", "description", "amountMoney", "link", "entrepreneur.startupName");
		model.setAttribute("investmentId", investmentId);
		model.setAttribute("activities", activities);
		model.setAttribute("applications", applications);
		model.setAttribute("accountingRecords", accountingRecords);

	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		InvestmentRound result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
