
package acme.features.authenticated.forumUser;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.forumUser.ForumUser;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/forum-user/")
public class AuthenticatedForumUserController extends AbstractController<Authenticated, ForumUser> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedForumUserListService	listService;

	@Autowired
	private AuthenticatedForumUserShowService	showService;

	@Autowired
	private AuthenticatedForumUserCreateService	createService;

	@Autowired
	private AuthenticatedForumUserDeleteService	deleteService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
