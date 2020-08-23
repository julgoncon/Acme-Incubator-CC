
package acme.features.administrator.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.Banner;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorBannerShowService implements AbstractShowService<Administrator, Banner> {

	//Internal states-------------------

	@Autowired
	private AdministratorBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;
		return true;
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
