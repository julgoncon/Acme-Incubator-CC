
package acme.features.anonymous.notice;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.notice.Notice;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/notice/")
public class AnonymousNoticeController extends AbstractController<Anonymous, Notice> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousNoticeListService	listService;

	@Autowired
	private AnonymousNoticeShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_ACTIVE, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
