
package acme.features.anonymous.gonzalezBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletin.GonzalezBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousGonzalezBulletinRepository extends AbstractRepository {

	@Query("select g from GonzalezBulletin g")
	Collection<GonzalezBulletin> findMany();
}
