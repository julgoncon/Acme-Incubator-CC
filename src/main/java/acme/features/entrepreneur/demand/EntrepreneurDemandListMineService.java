
package acme.features.entrepreneur.demand;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.demand.Demand;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneurDemandListMineService implements AbstractListService<Entrepreneur, Demand> {

	@Autowired
	EntrepreneurDemandRepository repository;


	@Override
	public boolean authorise(final Request<Demand> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Demand> request, final Demand entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "text");

	}

	@Override
	public Collection<Demand> findMany(final Request<Demand> request) {
		assert request != null;

		Collection<Demand> result;
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findDemandsByEntrepreneurId(principal.getActiveRoleId());

		return result;
	}

}
