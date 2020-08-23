/*
 * AnonymousCompanyRecordCreateService.java
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorDashboardRepository repository;

	// AbstractListService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberTechnologyRecordBySector", "numberToolRecordBySector", "getTotalNotice", "getTotalTechnologyRecord", "getTotalToolRecord", "getMinimumMoneyOfActiveOverture", "getMaximumMoneyOfActiveOverture",
			"getAverageMoneyOfActiveOverture", "getStandarddeviationMoneyOfActiveOverture", "getMinimumMoneyOfActiveInquiry", "getMaximumMoneyOfActiveInquiry", "getAverageMoneyOfActiveInquiry", "getStandarddeviationMoneyOfActiveInquiry",
			"ratioOfOpenSourceTechnologyRecord", "ratioOfOpenSourceToolRecord", "ratioOfCloseSourceTechnologyRecord", "ratioOfCloseSourceToolRecord", "getAverageInvestmentRoundPerEntrepreneur", "getAverageApplicationsPerEntrepreneur",
			"getAverageApplicationsPerInvestor", "ratioOfPendingApplications", "ratioOfAcceptedApplications", "ratioOfRejectedApplications", "ratioOfSeedInvestmentRound", "ratioOfAngelInvestmentRound", "ratioOfSeriesAInvestmentRound",
			"ratioOfSeriesBInvestmentRound", "ratioOfSeriesCInvestmentRound", "ratioOfBridgeInvestmentRound", "timeSeriesPending", "timeSeriesAccepted", "timeSeriesRejected");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result = new Dashboard();

		Collection<Object[]> array = this.repository.numberTechnologyRecordBySector();
		Collection<Object[]> array2 = this.repository.numberToolRecordBySector();
		Integer TotalNotice = this.repository.getTotalNotice();
		Integer TotalTechnologyRecord = this.repository.getTotalTechnologyRecord();
		Integer TotalToolRecord = this.repository.getTotalToolRecord();
		Double MinimumMoneyOfActiveOverture = this.repository.getMinimumMoneyOfActiveOverture();
		Double MaximumMoneyOfActiveOverture = this.repository.getMaximumMoneyOfActiveOverture();
		Double AverageMoneyOfActiveOverture = this.repository.getAverageMoneyOfActiveOverture();
		Double StandarddeviationMoneyOfActiveOverture = this.repository.getStandarddeviationMoneyOfActiveOverture();
		Double MinimumMoneyOfActiveInquiry = this.repository.getMinimumMoneyOfActiveInquiry();
		Double MaximumMoneyOfActiveInquiry = this.repository.getMaximumMoneyOfActiveInquiry();
		Double AverageMoneyOfActiveInquiry = this.repository.getAverageMoneyOfActiveInquiry();
		Double StandarddeviationMoneyOfActiveInquiry = this.repository.getStandarddeviationMoneyOfActiveInquiry();
		Double ratioOfOpenSourceToolRecord = this.repository.ratioOfOpenSourceToolRecord();
		Double ratioOfOpenSourceTechnologyRecord = this.repository.ratioOfOpenSourceTechnologyRecord();
		Double ratioOfCloseSourceToolRecord = this.repository.ratioOfCloseSourceToolRecord();
		Double ratioOfCloseSourceTechnologyRecord = this.repository.ratioOfCloseSourceTechnologyRecord();
		Double getAverageInvestmentRoundPerEntrepreneur = this.repository.getAverageInvestmentRoundPerEntrepreneur();
		Double getAverageApplicationsPerEntrepreneur = this.repository.getAverageApplicationsPerEntrepreneur();
		Double getAverageApplicationsPerInvestor = this.repository.getAverageApplicationsPerInvestor();
		Double ratioOfPendingApplications = this.repository.ratioOfPendingApplications();
		Double ratioOfAcceptedApplications = this.repository.ratioOfAcceptedApplications();
		Double ratioOfRejectedApplications = this.repository.ratioOfRejectedApplications();
		Double ratioOfSeedInvestmentRound = this.repository.ratioOfSeedInvestmentRound();
		Double ratioOfAngelInvestmentRound = this.repository.ratioOfAngelInvestmentRound();
		Double ratioOfSeriesAInvestmentRound = this.repository.ratioOfSeriesAInvestmentRound();
		Double ratioOfSeriesBInvestmentRound = this.repository.ratioOfSeriesBInvestmentRound();
		Double ratioOfSeriesCInvestmentRound = this.repository.ratioOfSeriesCInvestmentRound();
		Double ratioOfBridgeInvestmentRound = this.repository.ratioOfBridgeInvestmentRound();
		result.setNumberTechnologyRecordBySector(array);
		result.setNumberToolRecordBySector(array2);
		result.setGetTotalNotice(TotalNotice);
		result.setGetTotalTechnologyRecord(TotalTechnologyRecord);
		result.setGetTotalToolRecord(TotalToolRecord);
		result.setGetMinimumMoneyOfActiveOverture(MinimumMoneyOfActiveOverture);
		result.setGetMaximumMoneyOfActiveOverture(MaximumMoneyOfActiveOverture);
		result.setGetAverageMoneyOfActiveOverture(AverageMoneyOfActiveOverture);
		result.setGetStandarddeviationMoneyOfActiveOverture(StandarddeviationMoneyOfActiveOverture);
		result.setGetMinimumMoneyOfActiveInquiry(MinimumMoneyOfActiveInquiry);
		result.setGetMaximumMoneyOfActiveInquiry(MaximumMoneyOfActiveInquiry);
		result.setGetAverageMoneyOfActiveInquiry(AverageMoneyOfActiveInquiry);
		result.setGetStandarddeviationMoneyOfActiveInquiry(StandarddeviationMoneyOfActiveInquiry);
		result.setRatioOfOpenSourceToolRecord(ratioOfOpenSourceToolRecord);
		result.setRatioOfOpenSourceTechnologyRecord(ratioOfOpenSourceTechnologyRecord);
		result.setRatioOfCloseSourceToolRecord(ratioOfCloseSourceToolRecord);
		result.setGetAverageInvestmentRoundPerEntrepreneur(getAverageInvestmentRoundPerEntrepreneur);
		result.setGetAverageApplicationsPerEntrepreneur(getAverageApplicationsPerEntrepreneur);
		result.setGetAverageApplicationsPerInvestor(getAverageApplicationsPerInvestor);
		result.setRatioOfPendingApplications(ratioOfPendingApplications);
		result.setRatioOfAcceptedApplications(ratioOfAcceptedApplications);
		result.setRatioOfRejectedApplications(ratioOfRejectedApplications);
		result.setRatioOfSeedInvestmentRound(ratioOfSeedInvestmentRound);
		result.setRatioOfAngelInvestmentRound(ratioOfAngelInvestmentRound);
		result.setRatioOfSeriesAInvestmentRound(ratioOfSeriesAInvestmentRound);
		result.setRatioOfSeriesBInvestmentRound(ratioOfSeriesBInvestmentRound);
		result.setRatioOfSeriesCInvestmentRound(ratioOfSeriesCInvestmentRound);
		result.setRatioOfBridgeInvestmentRound(ratioOfBridgeInvestmentRound);
		result.setRatioOfCloseSourceTechnologyRecord(ratioOfCloseSourceTechnologyRecord);
		Collection<Integer> timeSeriesPending = this.timeSeriesPending();
		result.setTimeSeriesPending(timeSeriesPending);
		Collection<Integer> timeSeriesAccepted = this.timeSeriesAccepted();
		result.setTimeSeriesAccepted(timeSeriesAccepted);
		Collection<Integer> timeSeriesRejected = this.timeSeriesRejected();
		result.setTimeSeriesRejected(timeSeriesRejected);
		return result;
	}

	Collection<Integer> timeSeriesPending() {
		Calendar today = Calendar.getInstance();
		Collection<Application> apps = this.repository.getApplications();
		List<Integer> list = new ArrayList<Integer>(Collections.nCopies(15, 0));
		for (Application app : apps) {
			Date date = app.getCreation();
			Calendar dateApp = Calendar.getInstance();
			dateApp.setTime(date);
			long hoy = today.getTimeInMillis();
			long fecha = dateApp.getTimeInMillis();
			long diff = hoy - fecha;
			int milisCreation = (int) (diff / 86400000);

			Date date1 = app.getChangeStatus();
			int milisUpdate;
			if (date1 != null) {
				Calendar dateAppUpdate = Calendar.getInstance();
				dateAppUpdate.setTime(date1);
				milisUpdate = (int) (today.getTimeInMillis() - dateAppUpdate.getTimeInMillis());
				milisUpdate = milisUpdate / 86400000;
				if (milisUpdate >= 15) {
					continue;
				}
				if (milisCreation >= 15) {
					milisCreation = 14;
				}
				for (int i = milisUpdate; i <= milisCreation; i++) {
					list.set(i, list.get(i) + 1);
				}
			} else {
				if (milisCreation >= 15) {
					for (int i = 0; i <= 14; i++) {
						list.set(i, list.get(i) + 1);
					}
				} else {
					for (int i = 0; i <= milisCreation; i++) {
						list.set(i, list.get(i) + 1);
					}
				}
			}

		}
		return list;
	}

	Collection<Integer> timeSeriesAccepted() {
		Calendar today = Calendar.getInstance();
		Collection<Application> apps = this.repository.getApplications();
		List<Integer> list = new ArrayList<Integer>(Collections.nCopies(15, 0));
		for (Application app : apps) {
			if (app.getChangeStatus() == null || app.getStatus().equals("rejected")) {
				continue;
			} else {
				Date date = app.getChangeStatus();
				Calendar dateApp = Calendar.getInstance();
				dateApp.setTime(date);
				long hoy = today.getTimeInMillis();
				long fecha = dateApp.getTimeInMillis();
				long diff = hoy - fecha;
				int diasUpdate = (int) (diff / 86400000);
				if (diasUpdate >= 15) {
					for (int i = 0; i <= 14; i++) {
						list.set(i, list.get(i) + 1);
					}
				} else {
					for (int i = 0; i <= diasUpdate; i++) {
						list.set(i, list.get(i) + 1);
					}
				}

			}
		}
		return list;
	}

	Collection<Integer> timeSeriesRejected() {
		Calendar today = Calendar.getInstance();
		Collection<Application> apps = this.repository.getApplications();
		List<Integer> list = new ArrayList<Integer>(Collections.nCopies(15, 0));
		for (Application app : apps) {
			if (app.getChangeStatus() == null || app.getStatus().equals("accepted")) {
				continue;
			} else {
				Date date = app.getChangeStatus();
				Calendar dateApp = Calendar.getInstance();
				dateApp.setTime(date);
				long hoy = today.getTimeInMillis();
				long fecha = dateApp.getTimeInMillis();
				long diff = hoy - fecha;
				int diasUpdate = (int) (diff / 86400000);
				if (diasUpdate >= 15) {
					for (int i = 0; i <= 14; i++) {
						list.set(i, list.get(i) + 1);
					}
				} else {
					for (int i = 0; i <= diasUpdate; i++) {
						list.set(i, list.get(i) + 1);
					}
				}

			}
		}
		return list;
	}
}
