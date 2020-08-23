
package acme.features.patron.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.Banner;
import acme.entities.roles.Patron;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class PatronBannerShowService implements AbstractShowService<Patron, Banner> {

	//Internal states-------------------

	@Autowired
	private PatronBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		int bannerId = request.getModel().getInteger("id");
		Banner banner = this.repository.findOneById(bannerId);
		int principalId = request.getPrincipal().getAccountId();
		Boolean isMine = banner.getPatron().getUserAccount().getId() == principalId;
		return isMine;
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int id = request.getModel().getInteger("id");
		Banner banner = this.repository.findOneById(id);
		Boolean hasCreditCard = banner.getCreditCard() != null;
		request.unbind(entity, model, "picture", "slogan", "targetURL", "patron.organisationName", "creditCard.holderName", "creditCard.brandName", "creditCard.number", "creditCard.expirationMonth", "creditCard.expirationYear", "creditCard.cvv");
		model.setAttribute("hasCreditCard", hasCreditCard);
	}

	@Override
	public Banner findOne(final Request<Banner> request) {
		assert request != null;

		Banner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
