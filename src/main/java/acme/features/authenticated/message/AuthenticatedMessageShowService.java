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

package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedMessageRepository repository;

	// AbstractCreateService<Authenticated, Announcement> ---------------------------


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		int messageId = request.getModel().getInteger("id");
		Message msg = this.repository.findOneById(messageId);
		int forumId = msg.getForum().getId();
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
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		int forumId = entity.getForum().getId();
		request.unbind(entity, model, "title", "creation", "tags", "body", "authenticated.userAccount.username");
		model.setAttribute("forumId", forumId);
	}

	@Override
	public Message findOne(final Request<Message> request) {
		assert request != null;

		Message result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
