
package acme.features.authenticated.forum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.forum.Forum;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/forum/")
public class AuthenticatedForumController extends AbstractController<Authenticated, Forum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedForumListInvolvedService	listService;

	@Autowired
	private AuthenticatedForumShowService			showService;

	@Autowired
	private AuthenticatedForumDeleteService			deleteService;

	@Autowired
	private AuthenticatedForumCreateService			createService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_INVOLVED, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
