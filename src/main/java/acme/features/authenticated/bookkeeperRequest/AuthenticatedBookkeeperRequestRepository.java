
package acme.features.authenticated.bookkeeperRequest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBookkeeperRequestRepository extends AbstractRepository {

	@Query("select a.userAccount from Authenticated a where a.userAccount.id = ?1")
	UserAccount findAuthenticatedUAById(int id);

	@Query("select count(a) from BookkeeperRequest a where a.userAccount.id = ?1")
	int numberBookkeeperRequestsByUA(int id);

}
