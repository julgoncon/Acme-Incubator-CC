
package acme.features.bookkeeper.activity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activity.Activity;
import acme.entities.investmentRound.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperActivityRepository extends AbstractRepository {

	@Query("select a from Activity a where a.investmentRound.id=?1")
	Collection<Activity> findManyByInvestmentRound(int id);

	@Query("select a from Activity a where a.id = ?1 ")
	Activity findOneById(int id);

	@Query("select a from InvestmentRound a where a.id = ?1 ")
	InvestmentRound findOneInvestmentRoundById(int id);

}
