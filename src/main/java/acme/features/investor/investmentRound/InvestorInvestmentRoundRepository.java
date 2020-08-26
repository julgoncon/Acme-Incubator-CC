
package acme.features.investor.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.activity.Activity;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.demand.Demand;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorInvestmentRoundRepository extends AbstractRepository {

	@Query("select a from InvestmentRound a where a.entrepreneur.id = ?1")
	Collection<InvestmentRound> findManyByEntrepreneurId(int id);

	@Query("select a from InvestmentRound a where a.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select a from Entrepreneur a where a.userAccount.id = ?1")
	Entrepreneur findOneEntrepreneurByUAId(int id);

	@Query("select cp from CustomisationParameter cp")
	CustomisationParameter findCustomisationParameters();

	@Query("select a.ticker from InvestmentRound a")
	Collection<String> findAllTickers();

	@Query("select a from Activity a where a.investmentRound.id=?1")
	Collection<Activity> findActivitiesByInvestmentRound(int id);

	@Query("select a from InvestmentRound a where a.ticker=?1")
	InvestmentRound findInvestmentRoundByTicker(String ticker);

	@Query("select count(a) from Application a where a.investmentRound.id = ?1")
	Integer countApplicationsByInvestmentRoundId(int id);

	@Query("select a from InvestmentRound a where a.finalMode = true")
	Collection<InvestmentRound> findManyPublic();

	@Query("select a from Demand a where a.investmentRound.id=?1")
	Demand findDemandByInvestmentId(int id);

	@Query("select a from AccountingRecord a where a.investmentRound.id=?1")
	Collection<AccountingRecord> findAccountingRecordsByInvestmentRound(int id);

}
