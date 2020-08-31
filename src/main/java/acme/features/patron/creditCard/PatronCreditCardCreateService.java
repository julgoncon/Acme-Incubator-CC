
package acme.features.patron.creditCard;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class PatronCreditCardCreateService implements AbstractCreateService<Patron, CreditCard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	PatronCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "holderName", "brandName", "number", "expirationMonth", "expirationYear", "cvv");
	}

	@Override
	public CreditCard instantiate(final Request<CreditCard> request) {
		CreditCard result;

		result = new CreditCard();

		return result;
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors()) {

			boolean checkExpirationYear = false;
			boolean checkExpirationMonth = false;
			Calendar calendar = new GregorianCalendar();
			int actualYear = calendar.get(Calendar.YEAR);
			int actualMonth = calendar.get(Calendar.MONTH) + 1;
			actualYear = actualYear % 100;

			if (entity.getExpirationYear() > actualYear || entity.getExpirationYear() == actualYear && entity.getExpirationMonth() >= actualMonth) {
				checkExpirationYear = true;
				checkExpirationMonth = true;
			}
			if (entity.getExpirationYear() < actualYear) {
				checkExpirationMonth = true;
			}

			errors.state(request, checkExpirationYear, "expirationYear", "patron.creditCard.create.errors.expirationYear");
			errors.state(request, checkExpirationMonth, "expirationMonth", "patron.creditCard.create.errors.expirationMonth");

		}

	}

	@Override
	public void create(final Request<CreditCard> request, final CreditCard entity) {

		// Asignamos la tarjeta de crédito al patrocinador logueado
		int patronId = request.getPrincipal().getActiveRoleId();
		Patron patron = this.repository.findOnePatronById(patronId);

		patron.setCreditCard(entity);

		this.repository.save(entity);
	}

}
