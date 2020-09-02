
package acme.features.investor.application;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.demand.Demand;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	@Autowired
	InvestorApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		Boolean result;
		InvestmentRound investmentRound;
		int investmentRoundId;

		investmentRoundId = request.getModel().getInteger("investmentId");
		investmentRound = this.repository.findOneInvestmentRoundById(investmentRoundId);
		result = false;
		if (investmentRound != null) {
			result = investmentRound.getFinalMode();
		}
		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation", "investor");

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int investmentId = request.getModel().getInteger("investmentId");
		Demand demand = this.repository.findDemandByInvestmentId(investmentId);
		Boolean hasDemand = demand != null;
		request.unbind(entity, model, "ticker", "investmentOffer", "statement", "offer", "moreInfo", "password");
		model.setAttribute("investmentId", investmentId);
		model.setAttribute("hasDemand", hasDemand);

	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;
		Application result = new Application();

		int investmentId = request.getModel().getInteger("investmentId");
		InvestmentRound inv = this.repository.findOneInvestmentRoundById(investmentId);
		result.setInvestmentRound(inv);
		int principalId = request.getPrincipal().getAccountId();
		Investor investor = this.repository.findOneInvestorByUAId(principalId);
		result.setInvestor(investor);
		result.setStatus("pending");

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("ticker")) {
			Boolean isValidFormat = true;
			Boolean isValidYear = true;
			Boolean isValidNumber = true;
			Boolean isValidAS = true;
			String ticker = entity.getTicker();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(new Date(System.currentTimeMillis()));
			Integer year = calendar.get(Calendar.YEAR);
			year = year % 100;
			String[] tickerSplit = ticker.split("-");
			isValidFormat = tickerSplit.length == 3;
			if (isValidFormat) {
				isValidYear = tickerSplit[1].equals(year.toString());
				isValidNumber = tickerSplit[2].matches("^[0-9]{6}$");
				int id = request.getPrincipal().getAccountId();
				Investor en = this.repository.findOneInvestorByUAId(id);
				String activitySector = en.getActivitySector();
				Integer size = activitySector.length();
				String substring;
				if (size < 3) {
					substring = activitySector.toUpperCase();
				} else {
					substring = activitySector.substring(0, 3).toUpperCase();
				}

				if (size > 2) {
					isValidAS = tickerSplit[0].equals(substring);
				}
				if (size == 2) {
					isValidAS = tickerSplit[0].equals(substring.concat("X"));
				}
				if (size == 1) {
					isValidAS = tickerSplit[0].equals(substring.concat("XX"));
				}
			}
			Collection<String> tickers = this.repository.findAllTickers();
			Boolean isUnique = true;
			for (String t : tickers) {
				if (t.equals(ticker)) {

					isUnique = false;
				}
			}
			errors.state(request, isValidFormat, "ticker", "investor.application.error.isValidFormat");
			errors.state(request, isValidYear, "ticker", "investor.application.error.isValidYear");
			errors.state(request, isValidNumber, "ticker", "investor.application.error.isValidNumber");
			errors.state(request, isValidAS, "ticker", "investor.application.error.isValidAS");

			errors.state(request, isUnique, "ticker", "investor.application.error.tickerUnique");
		}

		if (!errors.hasErrors("investmentOffer")) {

			boolean assertCurrency1 = entity.getInvestmentOffer().getCurrency().contentEquals("€") || entity.getInvestmentOffer().getCurrency().contentEquals("EUR");
			errors.state(request, assertCurrency1, "investmentOffer", "investor.application.error.investmentOffer");

		}

		if (!errors.hasErrors("offer") && !errors.hasErrors("moreInfo") && !errors.hasErrors("password")) {
			Boolean assertAllMissing = entity.getOffer() == "" && entity.getMoreInfo() == "" && entity.getPassword() == "";
			if (!assertAllMissing) {
				Boolean offerMissing = entity.getOffer() == "";
				errors.state(request, !offerMissing, "offer", "investor.application.error.offer");
				Boolean passWithoutLink = entity.getMoreInfo() == "" && entity.getPassword() != "";
				errors.state(request, !passWithoutLink, "moreInfo", "investor.application.error.moreInfo");

			}
		}
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(moment);

		this.repository.save(entity);

	}
}
