
package acme.features.bookkeeper.investmentRound;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/bookkeeper/investment-round/")
public class BookkeeperInvestmentRoundController extends AbstractController<Bookkeeper, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private BookkeeperInvestmentRoundListAccountingRecordService	listAccountingService;

	@Autowired
	private BookkeeperInvestmentRoundListNoAccountingRecordService	listNotAccountingService;

	@Autowired
	private BookkeeperInvestmentRoundShowService					showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_ACCOUNTING_RECORD, BasicCommand.LIST, this.listAccountingService);
		super.addCustomCommand(CustomCommand.LIST_NOT_ACCOUNTING_RECORD, BasicCommand.LIST, this.listNotAccountingService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
