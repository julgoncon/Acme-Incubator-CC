
package acme.features.entrepreneur.investmentRound;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.forum.Forum;
import acme.entities.forumUser.ForumUser;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.features.authenticated.forum.AuthenticatedForumRepository;
import acme.features.authenticated.forumUser.AuthenticatedForumUserRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurInvestmentRoundCreateService implements AbstractCreateService<Entrepreneur, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	AuthenticatedForumRepository			forumRepository;

	@Autowired
	AuthenticatedForumUserRepository		forumUserRepository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		return true;
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

		request.unbind(entity, model, "ticker", "creation", "kindRound", "title", "finalMode", "description", "amountMoney", "link", "kindRound");
	}

	@Override
	public InvestmentRound instantiate(final Request<InvestmentRound> request) {
		InvestmentRound result;

		int id = request.getPrincipal().getAccountId();
		Entrepreneur entrepreneur = this.repository.findOneEntrepreneurByUAId(id);
		result = new InvestmentRound();
		result.setFinalMode(false);
		result.setEntrepreneur(entrepreneur);

		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
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
				Entrepreneur en = this.repository.findOneEntrepreneurByUAId(id);
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
			errors.state(request, isValidFormat, "ticker", "entrepreneur.investmentRound.error.isValidFormat");
			errors.state(request, isValidYear, "ticker", "entrepreneur.investmentRound.error.isValidYear");
			errors.state(request, isValidNumber, "ticker", "entrepreneur.investmentRound.error.isValidNumber");
			errors.state(request, isValidAS, "ticker", "entrepreneur.investmentRound.error.isValidAS");

			errors.state(request, isUnique, "ticker", "entrepreneur.investmentRound.error.tickerUnique");
		}

		if (!errors.hasErrors("amountMoney")) {

			Boolean assertCurrency1 = entity.getAmountMoney().getCurrency().contentEquals("€") || entity.getAmountMoney().getCurrency().contentEquals("EUR");
			Boolean assertOver0 = !entity.getAmountMoney().getAmount().equals(0.0);
			errors.state(request, assertCurrency1, "amountMoney", "entrepreneur.investmentRound.error.amountMoney");
			errors.state(request, assertOver0, "amountMoney", "entrepreneur.investmentRound.error.amountMoneyOver0");

		}
		errors.state(request, !this.containsSpamWords(entity.getTitle()), "title", "entrepreneur.investmentRound.error.spam");
		errors.state(request, !this.containsSpamWords(entity.getDescription()), "description", "entrepreneur.investmentRound.error.spam");
		errors.state(request, !this.containsSpamWords(entity.getLink()), "link", "entrepreneur.investmentRound.error.spam");

	}

	@Override
	public void create(final Request<InvestmentRound> request, final InvestmentRound entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(moment);
		this.repository.save(entity);
		Forum forum = new Forum();
		String title = entity.getTitle();
		forum.setTitle(title);
		forum.setInvestmentRound(entity);
		this.forumRepository.save(forum);
		int idForum = forum.getId();
		int idUser = request.getPrincipal().getAccountId();

		this.createidForumUser(idForum, idUser);

	}

	public void createidForumUser(final int idForum, final int idUser) {
		ForumUser res;
		Forum forum;
		Authenticated user;

		forum = this.repository.findOneForumById(idForum);
		user = this.repository.findOneAuthById(idUser);

		res = new ForumUser();

		res.setForum(forum);
		res.setAuthenticated(user);
		res.setCreator(true);

		this.forumUserRepository.save(res);
	}

	public boolean containsSpamWords(final String text) {
		boolean res = false;
		CustomisationParameter customisationParameter = this.repository.findCustomisationParameters();
		String spamWords = customisationParameter.getSpamWords();
		String[] spamWordsArray = spamWords.split(",");
		List<String> spamWordsList = Arrays.asList(spamWordsArray);

		//Normalizamos el texto que nos pasan como parámetro
		String normalizedText = text.replaceAll("\\s+", " ").toLowerCase();

		int cont = 0;
		for (String palabra : spamWordsList) {
			String normalizedTextCopy = normalizedText;
			while (normalizedTextCopy.indexOf(palabra.trim()) > -1) {
				normalizedTextCopy = normalizedTextCopy.substring(normalizedTextCopy.indexOf(palabra.trim()) + palabra.trim().length(), normalizedTextCopy.length());
				cont++;
			}

			if (palabra.trim().contains(" ") && normalizedText.contains(palabra.trim())) {
				String palabraSpace = palabra.trim().replace(" ", "-");
				normalizedText = normalizedText.replace(palabra, palabraSpace);
			}

		}

		String[] textArray = normalizedText.split(" ");
		int numeroTotalPalabras = textArray.length;
		double umbral = (double) cont / (double) numeroTotalPalabras;
		double umbralSpam = customisationParameter.getSpamThreshold() / 100;
		if (umbral >= umbralSpam) {
			res = true;
		}

		return res;

	}

}
