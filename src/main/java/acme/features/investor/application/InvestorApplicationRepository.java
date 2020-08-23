
package acme.features.investor.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.application.Application;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.investor.id = ?1")
	Collection<Application> findManyApplicationByInvestorId(int id);

	@Query("select a from Application a where a.id = ?1")
	Application findOneById(int id);

	@Query("select a from InvestmentRound a where a.id = ?1")
	InvestmentRound findOneInvestmentRoundById(int id);

	@Query("select a from Investor a where a.userAccount.id = ?1")
	Investor findOneInvestorByUAId(int id);

	@Query("select a.ticker from InvestmentRound a")
	Collection<String> findAllTickers();

}
