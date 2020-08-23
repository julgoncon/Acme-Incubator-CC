
package acme.features.bookkeeper.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.investmentRound.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperInvestmentRoundRepository extends AbstractRepository {

	@Query("select distinct a.investmentRound from AccountingRecord a where a.bookkeeper.id = ?1 and a.investmentRound.finalMode=true")
	Collection<InvestmentRound> findManyAccountedByBookkeeperId(int id);

	@Query("select a from InvestmentRound a where a.finalMode=true")
	Collection<InvestmentRound> findManyInvestmentRound();

	@Query("select a.investmentRound from AccountingRecord a where a.bookkeeper.id != ?1")
	Collection<InvestmentRound> findManyNotAccountedByBookkeeperId(int id);

	@Query("select a from AccountingRecord a where a.investmentRound.id=?1")
	Collection<AccountingRecord> findAccountingRecordsByInvestmentRound(int id);

	@Query("select a from InvestmentRound a where a.id = ?1")
	InvestmentRound findOneById(int id);

}
