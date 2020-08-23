<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2 style="margin-bottom: 2rem;text-align: center;">
	<acme:message code="administrator.dashboard.form.title.timeSeriesRejected"/>
</h2>

<div>
	<canvas id="canvas9"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
				labels: [
					"1", "2", "3","4", "5", "6","7", "8", "9","10", "11", "12","13", "14", "15"
				],
			datasets: [
				{
					data: [
						<jstl:forEach var="iterator" items="${timeSeriesRejected}">
							<jstl:out value="${iterator}"/>,
						</jstl:forEach>
					]
				}
			]
		};
		var options = {
			scales: {
				yAxes:[
					{
						ticks: {	
							suggestedMin: 0
						}
					}
				]		
			},
			legend: {
				display: false
			}
		};
		var canvas, context;
		
		canvas = document.getElementById("canvas9");
		context = canvas.getContext("2d");
		new Chart(context, {
			type: "line",
			data: data,
			options: options
		});
	});
</script>
<br/>
<br/>
<br/>
<h2 style="margin-bottom: 2rem;text-align: center;">
	<acme:message code="administrator.dashboard.form.title.timeSeriesPending"/>
</h2>

<div>
	<canvas id="canvas7"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
				labels: [
					"1", "2", "3","4", "5", "6","7", "8", "9","10", "11", "12","13", "14", "15"
				],
			datasets: [
				{
					data: [
						<jstl:forEach var="iterator" items="${timeSeriesPending}">
							<jstl:out value="${iterator}"/>,
						</jstl:forEach>
					]
				}
			]
		};
		var options = {
			scales: {
				yAxes:[
					{
						ticks: {	
							suggestedMin: 0
						}
					}
				]		
			},
			legend: {
				display: false
			}
		};
		var canvas, context;
		
		canvas = document.getElementById("canvas7");
		context = canvas.getContext("2d");
		new Chart(context, {
			type: "line",
			data: data,
			options: options
		});
	});
</script>
<br/>
<br/>
<br/>
<h2 style="margin-bottom: 2rem;text-align: center;">
	<acme:message code="administrator.dashboard.form.title.timeSeriesAccepted"/>
</h2>

<div>
	<canvas id="canvas8"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
				labels: [
					"1", "2", "3","4", "5", "6","7", "8", "9","10", "11", "12","13", "14", "15"
				],
			datasets: [
				{
					data: [
						<jstl:forEach var="iterator" items="${timeSeriesAccepted}">
							<jstl:out value="${iterator}"/>,
						</jstl:forEach>
					]
				}
			]
		};
		var options = {
			scales: {
				yAxes:[
					{
						ticks: {	
							suggestedMin: 0
						}
					}
				]		
			},
			legend: {
				display: false
			}
		};
		var canvas, context;
		
		canvas = document.getElementById("canvas8");
		context = canvas.getContext("2d");
		new Chart(context, {
			type: "line",
			data: data,
			options: options
		});
	});
</script>
<br/>
<br/>
<br/>

<h2 style="margin-bottom: 2rem;text-align: center;">
	<acme:message code="administrator.dashboard.form.title.ApplicationStatus"/>
</h2>

<div>
	<canvas id="canvas5"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
			labels: [
				"Accepted", "Rejected", "Pending"
			],
			datasets: [
				{
					data: [
						<jstl:out value = "${ratioOfAcceptedApplications}" />,
						<jstl:out value = "${ratioOfRejectedApplications}" />,
						<jstl:out value = "${ratioOfPendingApplications}" />,
						
					]
				}
			]
		};
		var options = {
			scales: {
				yAxes:[
					{
						ticks: {	
							suggestedMin: 0.0
						}
					}
				]		
			},
			legend: {
				display: false
			}
		};
		var canvas, context;
		
		canvas = document.getElementById("canvas5");
		context = canvas.getContext("2d");
		new Chart(context, {
			type: "bar",
			data: data,
			options: options
		});
	});
</script>
<br/>
<br/>
<br/>

<h2 style="margin-bottom: 2rem;text-align: center;">
	<acme:message code="administrator.dashboard.form.title.investmentRoundKind"/>
</h2>

<div>
	<canvas id="canvas6"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
			labels: [
				"Seed", "Angel", "Series-A", "Series-B", "Series-C", "Bridge"
			],
			datasets: [
				{
					data: [
						<jstl:out value = "${ratioOfSeedInvestmentRound}" />,
						<jstl:out value = "${ratioOfAngelInvestmentRound}" />,
						<jstl:out value = "${ratioOfSeriesAInvestmentRound}" />,
						<jstl:out value = "${ratioOfSeriesBInvestmentRound}" />,
						<jstl:out value = "${ratioOfSeriesCInvestmentRound}" />,
						<jstl:out value = "${ratioOfBridgeInvestmentRound}" />,
						
					]
				}
			]
		};
		var options = {
			scales: {
				yAxes:[
					{
						ticks: {	
							suggestedMin: 0.0
						}
					}
				]		
			},
			legend: {
				display: false
			}
		};
		var canvas, context;
		
		canvas = document.getElementById("canvas6");
		context = canvas.getContext("2d");
		new Chart(context, {
			type: "bar",
			data: data,
			options: options
		});
	});
</script>
<br/>
<br/>
<br/>

<h2 style="margin-bottom: 2rem;text-align: center;">
	<acme:message code="administrator.dashboard.form.title.numberTechnologyRecordBySector"/>
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
			labels: [
				<jstl:forEach var="iterator" items="${numberTechnologyRecordBySector}">
					"<jstl:out value="${iterator[0]}"/>",
				</jstl:forEach>
			],
			datasets: [
				{
					data: [
						<jstl:forEach var="iterator" items="${numberTechnologyRecordBySector}">
							<jstl:out value="${iterator[1]}"/>,
						</jstl:forEach>
					]
				}
			]
		};
		var options = {
			scales: {
				yAxes:[
					{
						ticks: {	
							suggestedMin: 0.0
						}
					}
				]		
			},
			legend: {
				display: false
			}
		};
		var canvas, context;
		
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type: "bar",
			data: data,
			options: options
		});
	});
</script>

<br/>
<br/>
<br/>

<h2 style="margin-bottom: 2rem;text-align: center;">
	<acme:message code="administrator.dashboard.form.title.numberToolRecordBySector"/>
</h2>

<div>
	<canvas id="canvas2"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
			labels: [
				<jstl:forEach var="iterator" items="${numberToolRecordBySector}">
					"<jstl:out value="${iterator[0]}"/>",
				</jstl:forEach>
			],
			datasets: [
				{
					data: [
						<jstl:forEach var="iterator" items="${numberToolRecordBySector}">
							<jstl:out value="${iterator[1]}"/>,
						</jstl:forEach>
					]
				}
			]
		};
		var options = {
			scales: {
				yAxes:[
					{
						ticks: {	
							suggestedMin: 0.0
						}
					}
				]		
			},
			legend: {
				display: false
			}
		};
		var canvas, context;
		
		canvas = document.getElementById("canvas2");
		context = canvas.getContext("2d");
		new Chart(context, {
			type: "bar",
			data: data,
			options: options
		});
	});
</script>

<br/>
<br/>
<br/>

       
<h2 style="margin-bottom: 2rem;text-align: center;">
	<acme:message code="administrator.dashboard.form.title.TechnologyRecordByOpenAndCloseSource"/>
</h2>

<div>
	<canvas id="canvas3"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
			labels: [
				"Open Source", "Close Source"
			],
			datasets: [
				{
					data: [
						<jstl:out value = "${ratioOfOpenSourceTechnologyRecord}" />,
						<jstl:out value = "${ratioOfCloseSourceTechnologyRecord}" />,
						
					]
				}
			]
		};
		var options = {
			scales: {
				yAxes:[
					{
						ticks: {	
							suggestedMin: 0.0
						}
					}
				]		
			},
			legend: {
				display: false
			}
		};
		var canvas, context;
		
		canvas = document.getElementById("canvas3");
		context = canvas.getContext("2d");
		new Chart(context, {
			type: "bar",
			data: data,
			options: options
		});
	});
</script>
<br/>
<br/>
<br/>


<h2 style="margin-bottom: 2rem;text-align: center;">
	<acme:message code="administrator.dashboard.form.title.ToolRecordByOpenAndCloseSource"/>
</h2>

<div>
	<canvas id="canvas4"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
			labels: [
				"Open Source", "Close Source"
			],
			datasets: [
				{
					data: [
						<jstl:out value = "${ratioOfOpenSourceToolRecord}" />,
						<jstl:out value = "${ratioOfCloseSourceToolRecord}" />,
						
					]
				}
			]
		};
		var options = {
			scales: {
				yAxes:[
					{
						ticks: {	
							suggestedMin: 0.0
						}
					}
				]		
			},
			legend: {
				display: false
			}
		};
		var canvas, context;
		
		canvas = document.getElementById("canvas4");
		context = canvas.getContext("2d");
		new Chart(context, {
			type: "bar",
			data: data,
			options: options
		});
	});
</script>
<br/>
<br/>
<br/>

<h3 style="margin-bottom: 2rem;text-align: center;">
	<acme:message code="administrator.dashboard.list.total"/>
</h3>

<table class="table table-striped table-condensed table-hover nowrap w-100">
    <tr>
        <th style="width: 33%"><acme:message code="administrator.dashboard.list.label.totalNotice"/></th>
        <th style="width: 33%"><acme:message code="administrator.dashboard.list.label.totalTechnologyRecord"/></th>
        <th style="width: 33%"><acme:message code="administrator.dashboard.list.label.totalToolRecord"/></th>
    </tr>
    <tr>
        <td>${getTotalNotice}</td>
        <td>${getTotalTechnologyRecord}</td>
        <td>${getTotalToolRecord}</td>
    </tr>
</table>


<h3 style="margin-bottom: 2rem;text-align: center;">
	<acme:message code="administrator.dashboard.label.average"/>
</h3>

<table class="table table-striped table-condensed table-hover nowrap w-100">
    <tr>
        <th style="width: 20%"><acme:message code="administrator.dashboard.list.label.MoneyOfActiveOverture"/></th>
        <th style="width: 20%"><acme:message code="administrator.dashboard.list.label.MoneyOfActiveInquiry"/></th>
    	<th style="width: 20%"><acme:message code="administrator.dashboard.list.label.investmentRoundPerEntrepreneur"/></th>
        <th style="width: 20%"><acme:message code="administrator.dashboard.list.label.applicationsPerEntrepreneur"/></th>
 		<th style="width: 20%"><acme:message code="administrator.dashboard.list.label.applicationsPerInvestor"/></th>
 
    </tr>
    <tr>
        <td>${getAverageMoneyOfActiveOverture}</td>
        <td>${getAverageMoneyOfActiveInquiry}</td>
        <td>${getAverageInvestmentRoundPerEntrepreneur}</td>
        <td>${getAverageApplicationsPerEntrepreneur}</td>
        <td>${getAverageApplicationsPerInvestor}</td>
        
    </tr>
</table>

<h3 style="margin-bottom: 2rem;text-align: center;">
	<acme:message code="administrator.dashboard.label.minimum"/>
</h3>

<table class="table table-striped table-condensed table-hover nowrap w-100">
    <tr>
        <th style="width: 50%"><acme:message code="administrator.dashboard.list.label.MoneyOfActiveOverture"/></th>
        <th style="width: 50%"><acme:message code="administrator.dashboard.list.label.MoneyOfActiveInquiry"/></th>
    </tr>
    <tr>
        <td>${getMinimumMoneyOfActiveOverture}</td>
        <td>${getMinimumMoneyOfActiveInquiry}</td>
        
    </tr>
</table>

<h3 style="margin-bottom: 2rem;text-align: center;">
	<acme:message code="administrator.dashboard.label.maximum"/>
</h3>

<table class="table table-striped table-condensed table-hover nowrap w-100">
    <tr>
        <th style="width: 50%"><acme:message code="administrator.dashboard.list.label.MoneyOfActiveOverture"/></th>
        <th style="width: 50%"><acme:message code="administrator.dashboard.list.label.MoneyOfActiveInquiry"/></th>
    </tr>
    <tr>
        <td>${getMaximumMoneyOfActiveOverture}</td>
        <td>${getMaximumMoneyOfActiveInquiry}</td>
        
    </tr>
</table>

<h3 style="margin-bottom: 2rem;text-align: center;">
	<acme:message code="administrator.dashboard.label.stddev"/>
</h3>

<table class="table table-striped table-condensed table-hover nowrap w-100">
    <tr>
        <th style="width: 50%"><acme:message code="administrator.dashboard.list.label.MoneyOfActiveOverture"/></th>
        <th style="width: 50%"><acme:message code="administrator.dashboard.list.label.MoneyOfActiveInquiry"/></th>
    </tr>
    <tr>
        <td>${getStandarddeviationMoneyOfActiveOverture}</td>
        <td>${getStandarddeviationMoneyOfActiveInquiry}</td>
        
    </tr>
</table>

