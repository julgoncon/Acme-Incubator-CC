
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.activity.Activity;
import acme.entities.application.Application;
import acme.entities.forum.Forum;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.features.authenticated.forum.AuthenticatedForumRepository;
import acme.features.entrepreneur.accountingRecord.EntrepreneurAccountingRecordRepository;
import acme.features.entrepreneur.activity.EntrepreneurActivityRepository;
import acme.features.entrepreneur.application.EntrepreneurApplicationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneurInvestmentRoundDeleteService implements AbstractDeleteService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	EntrepreneurAccountingRecordRepository	accountingRecordRepository;

	@Autowired
	EntrepreneurActivityRepository			activityRepository;

	@Autowired
	EntrepreneurApplicationRepository		applicationRepository;

	@Autowired
	AuthenticatedForumRepository			forumRepository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		boolean result;
		int investmentRoundId;
		InvestmentRound investmentRound;
		Entrepreneur entrepreneur;
		Principal principal;
		Integer applications;

		investmentRoundId = request.getModel().getInteger("id");
		investmentRound = this.repository.findOneById(investmentRoundId);
		entrepreneur = investmentRound.getEntrepreneur();
		principal = request.getPrincipal();
		applications = this.repository.countApplicationsByInvestmentRoundId(investmentRoundId);
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId() && applications == 0;

		return result;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Integer investmentRoundId = request.getModel().getInteger("id");
		Integer applications = this.repository.countApplicationsByInvestmentRoundId(investmentRoundId);
		request.unbind(entity, model, "ticker", "creation", "kindRound", "title", "finalMode", "description", "amountMoney", "link", "entrepreneur.startupName");
		model.setAttribute("applications", applications);

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

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		Collection<Activity> acitvities = this.repository.activitiesByInvestmentRoundId(entity.getId());
		this.activityRepository.deleteAll(acitvities);
		Collection<Forum> forums = this.repository.forumsByInvestmentRoundId(entity.getId());
		this.forumRepository.deleteAll(forums);
		Collection<Application> applications = this.repository.applicationsByInvestmentRoundId(entity.getId());
		this.applicationRepository.deleteAll(applications);
		Collection<AccountingRecord> ac = this.repository.accountingrecordsByInvestmentRoundId(entity.getId());
		this.accountingRecordRepository.deleteAll(ac);

		this.repository.delete(entity);
	}

}
