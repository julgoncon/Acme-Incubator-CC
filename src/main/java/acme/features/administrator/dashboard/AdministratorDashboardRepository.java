/*
 * AnonymousUserAccountRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.application.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(a) from Notice a")
	Integer getTotalNotice();

	@Query("select count(a) from TechnologyRecord a")
	Integer getTotalTechnologyRecord();

	@Query("select count(a) from ToolRecord a")
	Integer getTotalToolRecord();

	@Query("select min((o.minMoney.amount + o.maxMoney.amount)/2) from Overture o where o.deadline > CURRENT_TIMESTAMP")
	Double getMinimumMoneyOfActiveOverture();

	@Query("select max((o.minMoney.amount + o.maxMoney.amount)/2) from Overture o where o.deadline > CURRENT_TIMESTAMP")
	Double getMaximumMoneyOfActiveOverture();

	@Query("select avg((o.minMoney.amount + o.maxMoney.amount)/2) from Overture o where o.deadline > CURRENT_TIMESTAMP")
	Double getAverageMoneyOfActiveOverture();

	@Query("select stddev((o.minMoney.amount + o.maxMoney.amount)/2) from Overture o where o.deadline > CURRENT_TIMESTAMP")
	Double getStandarddeviationMoneyOfActiveOverture();

	@Query("select min((o.minMoney.amount + o.maxMoney.amount)/2) from Inquiry o where o.deadline > CURRENT_TIMESTAMP")
	Double getMinimumMoneyOfActiveInquiry();

	@Query("select max((o.minMoney.amount + o.maxMoney.amount)/2) from Inquiry o where o.deadline > CURRENT_TIMESTAMP")
	Double getMaximumMoneyOfActiveInquiry();

	@Query("select avg((o.minMoney.amount + o.maxMoney.amount)/2) from Inquiry o where o.deadline > CURRENT_TIMESTAMP")
	Double getAverageMoneyOfActiveInquiry();

	@Query("select stddev((o.minMoney.amount + o.maxMoney.amount)/2) from Inquiry o where o.deadline > CURRENT_TIMESTAMP")
	Double getStandarddeviationMoneyOfActiveInquiry();

	@Query("select c.activitySector, count(c) from TechnologyRecord c group by c.activitySector")
	Collection<Object[]> numberTechnologyRecordBySector();

	@Query("select c.activitySector, count(c) from ToolRecord c group by c.activitySector")
	Collection<Object[]> numberToolRecordBySector();

	@Query("select 1.0 * count(a) / (select count(b) from TechnologyRecord b) from TechnologyRecord a where a.openSource = true")
	Double ratioOfOpenSourceTechnologyRecord();

	@Query("select 1.0 * count(a) / (select count(b) from TechnologyRecord b) from TechnologyRecord a where a.openSource = false")
	Double ratioOfCloseSourceTechnologyRecord();

	@Query("select 1.0 * count(a) / (select count(b) from ToolRecord b) from ToolRecord a where a.openSource = true")
	Double ratioOfOpenSourceToolRecord();

	@Query("select 1.0 * count(a) / (select count(b) from ToolRecord b) from ToolRecord a where a.openSource = false")
	Double ratioOfCloseSourceToolRecord();

	@Query("select avg(select count(i) from InvestmentRound i where i.entrepreneur.id = e.id) from Entrepreneur e")
	Double getAverageInvestmentRoundPerEntrepreneur();

	@Query("select avg(select count(a) from Application a where a.investmentRound.entrepreneur.id = e.id) from Entrepreneur e")
	Double getAverageApplicationsPerEntrepreneur();

	@Query("select avg(select count(a) from Application a where a.investor.id = i.id) from Investor i")
	Double getAverageApplicationsPerInvestor();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = 'pending'")
	Double ratioOfPendingApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = 'accepted'")
	Double ratioOfAcceptedApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = 'rejected'")
	Double ratioOfRejectedApplications();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindRound = 'SEED'")
	Double ratioOfSeedInvestmentRound();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindRound = 'ANGEL'")
	Double ratioOfAngelInvestmentRound();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindRound = 'SERIES-A'")
	Double ratioOfSeriesAInvestmentRound();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindRound = 'SERIES-B'")
	Double ratioOfSeriesBInvestmentRound();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindRound = 'SERIES-C'")
	Double ratioOfSeriesCInvestmentRound();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindRound = 'BRIDGE'")
	Double ratioOfBridgeInvestmentRound();

	@Query("select a from Application a")
	Collection<Application> getApplications();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from Demand a")
	Double ratioInvestmentsRoundsWithDemand();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.offer!=''")
	Double ratioApplicationsWithOffer();

	@Query("select count(a) from Application a")
	Double findAllApplications();

	@Query("select count(a) from Demand a")
	Double findAllDemands();

}
