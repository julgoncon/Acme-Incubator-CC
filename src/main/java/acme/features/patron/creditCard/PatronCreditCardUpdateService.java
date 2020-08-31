/*
 * SponsorCreditCardUpdateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

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
import acme.framework.services.AbstractUpdateService;

@Service
public class PatronCreditCardUpdateService implements AbstractUpdateService<Patron, CreditCard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private PatronCreditCardRepository repository;

	// AbstractUpdateService<Sponsor, CreditCard> interface ---------------


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		int creditCardId = request.getModel().getInteger("id");
		int principalId = request.getPrincipal().getAccountId();
		Patron patron = this.repository.findOnePatronByUserAccountId(principalId);
		Boolean isMine = false;
		if (patron.getCreditCard() != null) {
			isMine = patron.getCreditCard().getId() == creditCardId;
		}
		return isMine;

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
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;

		CreditCard result;

		int principalId = request.getPrincipal().getAccountId();

		Patron patron = this.repository.findOnePatronByUserAccountId(principalId);
		result = patron.getCreditCard();

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
	public void update(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		// Asignamos la tarjeta de crédito al patrocinador logueado
		int patronId = request.getPrincipal().getActiveRoleId();
		Patron patron = this.repository.findOnePatronById(patronId);

		patron.setCreditCard(entity);

		this.repository.save(entity);
	}

}
