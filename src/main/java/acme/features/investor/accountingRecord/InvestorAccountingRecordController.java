
package acme.features.investor.accountingRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.roles.Investor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/investor/accounting-record/")
public class InvestorAccountingRecordController extends AbstractController<Investor, AccountingRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private InvestorAccountingRecordListService	listService;

	@Autowired
	private InvestorAccountingRecordShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_INVESTMENT, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
