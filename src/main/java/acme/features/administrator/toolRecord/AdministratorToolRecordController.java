
package acme.features.administrator.toolRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.toolRecord.ToolRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/tool-record/")
public class AdministratorToolRecordController extends AbstractController<Administrator, ToolRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorToolRecordListService		listService;

	@Autowired
	private AdministratorToolRecordShowService		showService;

	@Autowired
	private AdministratorToolRecordUpdateService	updateService;

	@Autowired
	private AdministratorToolRecordCreateService	createService;

	@Autowired
	private AdministratorToolRecordDeleteService	deleteService;

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
