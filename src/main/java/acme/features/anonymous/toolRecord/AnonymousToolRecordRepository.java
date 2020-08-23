
package acme.features.anonymous.toolRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolRecord.ToolRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousToolRecordRepository extends AbstractRepository {

	@Query("select a from ToolRecord a")
	Collection<ToolRecord> findMany();

	@Query("select a from ToolRecord a where a.id = ?1")
	ToolRecord findOneById(int id);

}
