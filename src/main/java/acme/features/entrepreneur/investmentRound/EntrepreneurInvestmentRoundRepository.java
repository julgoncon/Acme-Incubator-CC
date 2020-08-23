
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.activity.Activity;
import acme.entities.application.Application;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.demand.Demand;
import acme.entities.forum.Forum;
import acme.entities.forumUser.ForumUser;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.message.Message;
import acme.entities.roles.Entrepreneur;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

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

	@Query("select a from AccountingRecord a where a.investmentRound.id=?1")
	Collection<AccountingRecord> findAccountingRecordsByInvestmentRound(int id);

	@Query("select a from InvestmentRound a where a.ticker=?1")
	InvestmentRound findInvestmentRoundByTicker(String ticker);

	@Query("select count(a) from Application a where a.investmentRound.id = ?1")
	Integer countApplicationsByInvestmentRoundId(int id);

	@Query("select a from Application a where a.investmentRound.id = ?1")
	Collection<Application> applicationsByInvestmentRoundId(int id);

	@Query("select a from AccountingRecord a where a.investmentRound.id = ?1")
	Collection<AccountingRecord> accountingrecordsByInvestmentRoundId(int id);

	@Query("select a from Activity a where a.investmentRound.id = ?1")
	Collection<Activity> activitiesByInvestmentRoundId(int id);

	@Query("select a from Forum a where a.investmentRound.id = ?1")
	Collection<Forum> forumsByInvestmentRoundId(int id);

	@Query("select a from Forum a where a.id = ?1")
	Forum findOneForumById(int id);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findOneAuthById(int id);

	@Query("select d from Demand d where d.investmentRound.id = ?1")
	Demand findDemandByInvestmentRoundId(int id);

	@Query("select a from Message a where a.forum.id = ?1")
	Collection<Message> findMessagesByForumId(int id);

	@Query("select a from ForumUser a where a.forum.id = ?1")
	Collection<ForumUser> findForumUsersByForumId(int id);

}
