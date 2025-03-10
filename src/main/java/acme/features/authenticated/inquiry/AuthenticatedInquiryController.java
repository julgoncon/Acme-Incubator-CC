
package acme.features.authenticated.inquiry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.inquiry.Inquiry;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/inquiry/")
public class AuthenticatedInquiryController extends AbstractController<Authenticated, Inquiry> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedInquiryListService	listService;

	@Autowired
	private AuthenticatedInquiryShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_ACTIVE, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
