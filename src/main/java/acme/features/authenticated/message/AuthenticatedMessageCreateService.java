
package acme.features.authenticated.message;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.forum.Forum;
import acme.entities.message.Message;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean res;
		Principal principal;

		principal = request.getPrincipal();
		res = principal.getActiveRole() == Authenticated.class;

		return res;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation", "forum", "authenticated");
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int forumId = request.getModel().getInteger("forumId");

		request.unbind(entity, model, "title", "tags", "body");

		model.setAttribute("forumId", forumId);
		model.setAttribute("confirm", false);
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		Message res;

		Principal principal;
		int userId;
		Authenticated user;

		principal = request.getPrincipal();
		userId = principal.getActiveRoleId();

		user = this.repository.findAuthenticatedById(userId);

		int forumId = request.getModel().getInteger("forumId");
		Forum forum = this.repository.findForumById(forumId);

		res = new Message();

		res.setAuthenticated(user);
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		res.setCreation(moment);
		res.setForum(forum);

		return res;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("creation")) {
			boolean assertMoment = entity.getCreation() != null && !entity.getCreation().after(new Date(System.currentTimeMillis()));
			errors.state(request, assertMoment, "creation", "authenticated.message.error.creation");
		}

		boolean assertConfirm = request.getModel().getBoolean("confirm");
		errors.state(request, assertConfirm, "confirm", "authenticated.message.error.confirm");

		errors.state(request, !this.containsSpamWords(entity.getTitle()), "title", "authenticated.message.form.error.spam");
		errors.state(request, !this.containsSpamWords(entity.getTags()), "tags", "authenticated.message.form.error.spam");
		errors.state(request, !this.containsSpamWords(entity.getBody()), "body", "authenticated.message.form.error.spam");

	}

	@Override
	public void create(final Request<Message> request, final Message entity) {

		this.repository.save(entity);
	}

	// Ancillary functions ----------------

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
