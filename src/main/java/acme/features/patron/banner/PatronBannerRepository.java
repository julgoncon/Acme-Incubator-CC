
package acme.features.patron.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.banner.Banner;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.roles.Patron;
import acme.framework.repositories.AbstractRepository;

public interface PatronBannerRepository extends AbstractRepository {

	@Query("select b from Banner b where b.id = ?1")
	Banner findOneById(int id);

	@Query("select cp from CustomisationParameter cp")
	CustomisationParameter findCustomisationParameters();

	@Query("select a from Banner a where a.patron.userAccount.id=?1")
	Collection<Banner> findMany(int id);

	@Query("select b from Patron b where b.id = ?1")
	Patron findOnePatronById(int id);

}
