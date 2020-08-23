
package acme.features.authenticated.technologyRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.technologyRecord.TechnologyRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/technology-record/")
public class AuthenticatedTechnologyRecordController extends AbstractController<Authenticated, TechnologyRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedTechnologyRecordListService	listService;

	@Autowired
	private AuthenticatedTechnologyRecordShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
