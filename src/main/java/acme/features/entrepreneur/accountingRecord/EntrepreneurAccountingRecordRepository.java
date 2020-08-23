
package acme.features.entrepreneur.accountingRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.investmentRound.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurAccountingRecordRepository extends AbstractRepository {

	@Query("select a from AccountingRecord a where a.investmentRound.id=?1 and a.draft=false")
	Collection<AccountingRecord> findManyByInvestmentRound(int id);

	@Query("select a from AccountingRecord a where a.id = ?1 ")
	AccountingRecord findOneById(int id);

	@Query("select a from InvestmentRound a where a.id = ?1 ")
	InvestmentRound findOneInvestmentRoundById(int id);

}
