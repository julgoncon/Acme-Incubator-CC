
package acme.features.entrepreneur.accountingRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/accounting-record/")
public class EntrepreneurAccountingRecordController extends AbstractController<Entrepreneur, AccountingRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneurAccountingRecordListService	listService;

	@Autowired
	private EntrepreneurAccountingRecordShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_INVESTMENT, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
