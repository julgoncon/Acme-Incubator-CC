
package acme.features.patron.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.Banner;
import acme.entities.roles.Patron;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class PatronBannerDeleteService implements AbstractDeleteService<Patron, Banner> {

	@Autowired
	PatronBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		Boolean result;

		Integer bannerId = request.getModel().getInteger("id");
		Banner banner = this.repository.findOneById(bannerId);
		int principalId = request.getPrincipal().getActiveRoleId();
		result = banner.getPatron().getId() == principalId;

		return result;
	}

	@Override
	public void bind(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creditCard.holderName", "creditCard.brandName", "creditCard.number", "creditCard.expirationMonth", "creditCard.expirationYear", "creditCard.cvv");

	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int principalId = request.getPrincipal().getActiveRoleId();
		Patron patron = this.repository.findOnePatronById(principalId);
		Boolean hasCreditCard = patron.getCreditCard() != null;
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

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Banner> request, final Banner entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
