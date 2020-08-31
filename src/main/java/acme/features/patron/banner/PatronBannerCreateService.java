
package acme.features.patron.banner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.Banner;
import acme.entities.creditCard.CreditCard;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.roles.Patron;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class PatronBannerCreateService implements AbstractCreateService<Patron, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	PatronBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "targetURL");
		model.setAttribute("addCreditCard", false);
	}

	@Override
	public Banner instantiate(final Request<Banner> request) {
		Banner result;

		result = new Banner();

		return result;
	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		errors.state(request, !this.containsSpamWords(entity.getPicture()), "picture", "patron.banner.error.spam");
		errors.state(request, !this.containsSpamWords(entity.getSlogan()), "slogan", "patron.banner.error.spam");
		errors.state(request, !this.containsSpamWords(entity.getTargetURL()), "targetURL", "patron.banner.error.spam");

	}

	@Override
	public void create(final Request<Banner> request, final Banner entity) {
		int patronId = request.getPrincipal().getActiveRoleId();
		Patron patron = this.repository.findOnePatronById(patronId);
		CreditCard creditCard = patron.getCreditCard();
		Boolean addCreditCard = request.getModel().getBoolean("addCreditCard");
		if (addCreditCard && creditCard != null) {
			CreditCard creditCardCopy = new CreditCard();
			creditCardCopy.setBrandName(creditCard.getBrandName());
			creditCardCopy.setCvv(creditCard.getCvv());
			creditCardCopy.setExpirationMonth(creditCard.getExpirationMonth());
			creditCardCopy.setExpirationYear(creditCard.getExpirationYear());
			creditCardCopy.setHolderName(creditCard.getHolderName());
			creditCardCopy.setNumber(creditCard.getNumber());

			this.repository.save(creditCardCopy);

			entity.setPatron(patron);
			entity.setCreditCard(creditCardCopy);
			this.repository.save(entity);
		} else {
			entity.setPatron(patron);
			this.repository.save(entity);
		}

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
