
package acme.features.authenticated.notice;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.notice.Notice;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedNoticeRepository extends AbstractRepository {

	@Query("select a from Notice a where a.deadline > CURRENT_TIMESTAMP")
	Collection<Notice> findManyActive();

	@Query("select a from Notice a where a.deadline > CURRENT_TIMESTAMP and a.id=?1")
	Notice findOneActive(int id);

	@Query("select a from Notice a where a.id = ?1")
	Notice findOneById(int id);

}
