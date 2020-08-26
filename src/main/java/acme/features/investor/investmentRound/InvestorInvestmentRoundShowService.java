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

package acme.features.investor.investmentRound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.demand.Demand;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class InvestorInvestmentRoundShowService implements AbstractShowService<Investor, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	@Autowired
	InvestorInvestmentRoundRepository repository;

	// AbstractCreateService<Authenticated, Announcement> ---------------------------


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		Boolean result;
		InvestmentRound investmentRound;
		int investmentRoundId;

		investmentRoundId = request.getModel().getInteger("id");
		investmentRound = this.repository.findOneById(investmentRoundId);
		result = investmentRound.getFinalMode();
		return result;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int investmentId = request.getModel().getInteger("id");
		int activities = this.repository.findActivitiesByInvestmentRound(investmentId).size();
		int accountingRecords = this.repository.findAccountingRecordsByInvestmentRound(investmentId).size();
		Demand demand = this.repository.findDemandByInvestmentId(investmentId);
		request.unbind(entity, model, "ticker", "creation", "kindRound", "title", "finalMode", "description", "amountMoney", "link", "entrepreneur.startupName");
		if (demand != null) {
			model.setAttribute("text", demand.getText());

		}
		model.setAttribute("activities", activities);
		model.setAttribute("accountingRecords", accountingRecords);
		model.setAttribute("investmentId", investmentId);

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
