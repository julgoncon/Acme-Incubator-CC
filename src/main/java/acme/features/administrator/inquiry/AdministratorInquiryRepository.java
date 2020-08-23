
package acme.features.administrator.inquiry;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inquiry.Inquiry;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorInquiryRepository extends AbstractRepository {

	@Query("select a from Inquiry a")
	Collection<Inquiry> findMany();

	@Query("select a from Inquiry a where a.id = ?1")
	Inquiry findOneById(int id);

}
