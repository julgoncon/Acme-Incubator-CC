
package acme.features.patron.creditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class PatronCreditCardShowService implements AbstractShowService<Patron, CreditCard> {

	//Internal states-------------------

	@Autowired
	private PatronCreditCardRepository repository;


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
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
