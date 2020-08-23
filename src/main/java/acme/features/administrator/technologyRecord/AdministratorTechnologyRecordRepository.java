
package acme.features.administrator.technologyRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.technologyRecord.TechnologyRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorTechnologyRecordRepository extends AbstractRepository {

	@Query("select a from TechnologyRecord a")
	Collection<TechnologyRecord> findMany();

	@Query("select a from TechnologyRecord a where a.id = ?1")
	TechnologyRecord findOneById(int id);

}
