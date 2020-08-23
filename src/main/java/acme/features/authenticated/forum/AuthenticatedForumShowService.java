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

package acme.features.authenticated.forum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forum.Forum;
import acme.entities.message.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedForumShowService implements AbstractShowService<Authenticated, Forum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedForumRepository repository;

	// AbstractCreateService<Authenticated, Announcement> ---------------------------


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		int forumId = request.getModel().getInteger("id");
		Collection<Authenticated> users = this.repository.findUsersByForumId(forumId);
		int userId = request.getPrincipal().getActiveRoleId();
		Boolean result = false;
		for (Authenticated auth : users) {
			if (auth.getId() == userId) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int forumId = request.getModel().getInteger("id");
		request.unbind(entity, model, "title");
		Principal principal = request.getPrincipal();
		int idPrincipal = principal.getActiveRoleId();
		int idCreator = this.repository.findIdCreatorUser(forumId);
		Boolean isCreator = idPrincipal == idCreator;
		model.setAttribute("isCreator", isCreator);
		model.setAttribute("forumId", forumId);
		Collection<Message> msgs = this.repository.findManyMessageByForumId(forumId);
		Boolean hasMsgs = msgs.size() > 0;
		model.setAttribute("hasMsgs", hasMsgs);

	}

	@Override
	public Forum findOne(final Request<Forum> request) {
		assert request != null;

		Forum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
