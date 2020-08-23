
package acme.features.entrepreneur.demand;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.demand.Demand;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/demand/")
public class EntrepreneurDemandController extends AbstractController<Entrepreneur, Demand> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneurDemandCreateService		createService;

	@Autowired
	private EntrepreneurDemandShowService		showService;

	@Autowired
	private EntrepreneurDemandUpdateService		updateService;

	@Autowired
	private EntrepreneurDemandDeleteService		deleteService;

	@Autowired
	private EntrepreneurDemandListMineService	listMineService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);

	}

}
