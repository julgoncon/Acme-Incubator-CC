
package acme.features.entrepreneur.demand;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.demand.Demand;
import acme.entities.investmentRound.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurDemandRepository extends AbstractRepository {

	@Query("select j from InvestmentRound j where j.id = ?1")
	InvestmentRound findInvestmentRoundById(int id);

	@Query("select d from Demand d where d.id = ?1")
	Demand findById(int id);

	@Query("select d from Demand d where d.investmentRound.id = ?1")
	Demand findDemandByInvestmentRoundId(int id);

	@Query("select d from Demand d where d.investmentRound.entrepreneur.id = ?1")
	Collection<Demand> findDemandsByEntrepreneurId(int id);
}
