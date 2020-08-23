
package acme.features.entrepreneur.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.forum.Forum;
import acme.entities.forumUser.ForumUser;
import acme.entities.roles.Entrepreneur;
import acme.features.authenticated.forumUser.AuthenticatedForumUserRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurApplicationUpdateService implements AbstractUpdateService<Entrepreneur, Application> {

	@Autowired
	EntrepreneurApplicationRepository	repository;

	@Autowired
	AuthenticatedForumUserRepository	forumUserRepository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int applicationId;
		Application application;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneById(applicationId);
		Entrepreneur entrepreneur = application.getInvestmentRound().getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "ticker", "creation", "investor", "investmentRound", "statement", "investmentOffer");
		if (entity.getStatus().contains("accepted")) {
			entity.setStatus("accepted");
		} else if (entity.getStatus().contains("pending")) {
			entity.setStatus("pending");
		} else if (entity.getStatus().contains("rejected")) {
			entity.setStatus("rejected");
		}
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "justification");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;
		int id;
		Application result;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (entity.getStatus().equals("rejected")) {
			Boolean notBlank;
			String justification = entity.getJustification().replace(" ", "");
			notBlank = justification.length() != 0;
			request.getModel().setAttribute("isJustificated", notBlank);
			errors.state(request, notBlank, "justification", "entrepreneur.request.error.must-justificated");

		}
	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;
		if (entity.getStatus().equals("accepted") || entity.getStatus().equals("rejected")) {
			Date moment;
			moment = new Date(System.currentTimeMillis() - 1);
			entity.setChangeStatus(moment);
		}

		this.repository.save(entity);
		Boolean entrepreneurAndInvestor = entity.getInvestor().getUserAccount().getId() == request.getPrincipal().getAccountId();
		if (entity.getStatus().equals("accepted") && !entrepreneurAndInvestor) {
			int userId = entity.getInvestor().getUserAccount().getId();
			Forum forum = this.repository.findOneForumByInvestmentRoundId(entity.getInvestmentRound().getId());
			this.createidForumUser(forum.getId(), userId);

		}

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
		res.setCreator(false);

		this.forumUserRepository.save(res);
	}
}
