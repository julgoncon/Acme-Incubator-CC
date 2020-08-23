
package acme.features.administrator.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.banner.Banner;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorBannerRepository extends AbstractRepository {

	@Query("select b from Banner b where b.id = ?1")
	Banner findOneById(int id);

	@Query("select a from Banner a")
	Collection<Banner> findMany();

}
