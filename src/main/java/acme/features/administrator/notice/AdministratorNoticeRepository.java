
package acme.features.administrator.notice;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.notice.Notice;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorNoticeRepository extends AbstractRepository {

	@Query("select a from Notice a")
	Collection<Notice> findMany();

	@Query("select a from Notice a where a.id = ?1")
	Notice findOneById(int id);

}
