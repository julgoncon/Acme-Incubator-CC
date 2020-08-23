
package acme.forms;

import java.io.Serializable;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	//Serialisation identifier-----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes--------------------------------------------------------------

	Collection<Object[]>		numberTechnologyRecordBySector;

	Collection<Object[]>		numberToolRecordBySector;

	Integer						getTotalNotice;

	Integer						getTotalTechnologyRecord;

	Integer						getTotalToolRecord;

	Double						getMinimumMoneyOfActiveOverture;

	Double						getMaximumMoneyOfActiveOverture;

	Double						getAverageMoneyOfActiveOverture;

	Double						getStandarddeviationMoneyOfActiveOverture;

	Double						getMinimumMoneyOfActiveInquiry;

	Double						getMaximumMoneyOfActiveInquiry;

	Double						getAverageMoneyOfActiveInquiry;

	Double						getStandarddeviationMoneyOfActiveInquiry;

	Double						ratioOfOpenSourceTechnologyRecord;

	Double						ratioOfOpenSourceToolRecord;

	Double						ratioOfCloseSourceTechnologyRecord;

	Double						ratioOfCloseSourceToolRecord;

	Double						getAverageInvestmentRoundPerEntrepreneur;

	Double						getAverageApplicationsPerEntrepreneur;

	Double						getAverageApplicationsPerInvestor;

	Double						ratioOfPendingApplications;

	Double						ratioOfAcceptedApplications;

	Double						ratioOfRejectedApplications;

	Double						ratioOfSeedInvestmentRound;

	Double						ratioOfAngelInvestmentRound;

	Double						ratioOfSeriesAInvestmentRound;

	Double						ratioOfSeriesBInvestmentRound;

	Double						ratioOfSeriesCInvestmentRound;

	Double						ratioOfBridgeInvestmentRound;

	Collection<Integer>			timeSeriesPending;

	Collection<Integer>			timeSeriesAccepted;

	Collection<Integer>			timeSeriesRejected;

	// Derived attributes ---------------------------------------------------

}
