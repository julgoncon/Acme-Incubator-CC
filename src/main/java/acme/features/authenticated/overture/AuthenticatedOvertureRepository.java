
package acme.features.authenticated.overture;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.overture.Overture;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedOvertureRepository extends AbstractRepository {

	@Query("select a from Overture a where a.deadline > CURRENT_TIMESTAMP")
	Collection<Overture> findManyActive();

	@Query("select a from Overture a where a.deadline > CURRENT_TIMESTAMP and a.id=?1")
	Overture findOneActive(int id);

	@Query("select a from Overture a where a.id = ?1")
	Overture findOneById(int id);

}
