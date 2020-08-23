
package acme.features.authenticated.technologyRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.technologyRecord.TechnologyRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedTechnologyRecordListService implements AbstractListService<Authenticated, TechnologyRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedTechnologyRecordRepository repository;

	// AbstractListService<Administrator, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<TechnologyRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<TechnologyRecord> request, final TechnologyRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "activitySector", "inventorName", "stars");

	}

	@Override
	public Collection<TechnologyRecord> findMany(final Request<TechnologyRecord> request) {
		assert request != null;

		Collection<TechnologyRecord> result = this.repository.findMany();

		return result;
	}

}
