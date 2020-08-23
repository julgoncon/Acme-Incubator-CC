
package acme.features.authenticated.forum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forum.Forum;
import acme.entities.forumUser.ForumUser;
import acme.entities.message.Message;
import acme.features.authenticated.forumUser.AuthenticatedForumUserRepository;
import acme.features.authenticated.message.AuthenticatedMessageRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedForumDeleteService implements AbstractDeleteService<Authenticated, Forum> {

	@Autowired
	AuthenticatedForumRepository		repository;

	@Autowired
	AuthenticatedMessageRepository		messageRepository;

	@Autowired
	AuthenticatedForumUserRepository	forumUserRepository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		boolean res;
		Principal principal;
		int idPrincipal;
		int idCreator;
		int idForum = request.getModel().getInteger("id");
		principal = request.getPrincipal();

		idPrincipal = principal.getActiveRoleId();
		idCreator = this.repository.findIdCreatorUser(idForum);

		res = idPrincipal == idCreator;

		return res;
	}

	@Override
	public void bind(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
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

	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int idForum = request.getModel().getInteger("id");
		int idCreator = this.repository.findIdCreatorUser(idForum);
		Principal principal = request.getPrincipal();
		int idPrincipal = principal.getActiveRoleId();

		boolean assertCreator = idPrincipal == idCreator;

		errors.state(request, assertCreator, "title", "authenticated.forum.form.error.creator");
	}

	@Override
	public void delete(final Request<Forum> request, final Forum entity) {
		assert request != null;
		assert entity != null;

		Collection<Message> messages = this.repository.findMessagesByForum(entity.getId());
		this.messageRepository.deleteAll(messages);
		Collection<ForumUser> forumUser = this.repository.findForumUserByForum(entity.getId());
		this.forumUserRepository.deleteAll(forumUser);

		this.repository.delete(entity);
	}

}
