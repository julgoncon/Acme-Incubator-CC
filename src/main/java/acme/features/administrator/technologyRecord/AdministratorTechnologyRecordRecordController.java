
package acme.features.administrator.technologyRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.technologyRecord.TechnologyRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/technology-record/")
public class AdministratorTechnologyRecordRecordController extends AbstractController<Administrator, TechnologyRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorTechnologyRecordListService	listService;

	@Autowired
	private AdministratorTechnologyRecordShowService	showService;

	@Autowired
	private AdministratorTechnologyRecordUpdateService	updateService;

	@Autowired
	private AdministratorTechnologyRecordCreateService	createService;

	@Autowired
	private AdministratorTechnologyRecordDeleteService	deleteService;

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
