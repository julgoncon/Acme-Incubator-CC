
package acme.features.authenticated.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.investmentRound.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInvestmentRoundRepository extends AbstractRepository {

	@Query("select a from InvestmentRound a where a.finalMode=true")
	Collection<InvestmentRound> findMany();

	@Query("select a from InvestmentRound a where a.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select a from AccountingRecord a where a.investmentRound.id=?1")
	Collection<AccountingRecord> findAccountingRecordsByInvestmentRound(int id);

}
