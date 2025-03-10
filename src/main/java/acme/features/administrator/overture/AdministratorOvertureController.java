
package acme.features.administrator.overture;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.overture.Overture;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/overture/")
public class AdministratorOvertureController extends AbstractController<Administrator, Overture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorOvertureListService	listService;

	@Autowired
	private AdministratorOvertureShowService	showService;

	@Autowired
	private AdministratorOvertureUpdateService	updateService;

	@Autowired
	private AdministratorOvertureCreateService	createService;

	@Autowired
	private AdministratorOvertureDeleteService	deleteService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
