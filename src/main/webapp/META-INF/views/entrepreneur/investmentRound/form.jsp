<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@page language="java" import="acme.framework.helpers.MessageHelper"%>

<acme:form>
	<acme:form-hidden path="activities" />
	<acme:form-hidden path="applications" />
	<acme:form-hidden path="investmentId" />
	<acme:form-hidden path="accountingRecords" />
	<jstl:if test="${command == 'create'}">
		<acme:form-hidden path="finalMode" />
	</jstl:if>
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.ticker" path="ticker" placeholder="SSS-YY-NNNNNN"/>
	<jstl:out value="${MessageHelper.getMessage('entrepreneur.investmentRound.form.label.tickerExplanation')}"/>
	<jstl:if test="${command == 'show' || command=='update'}">
	<acme:form-moment code="entrepreneur.investmentRound.form.label.creation" path="creation" readonly="true"/>
	</jstl:if>
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.kindRound" path="kindRound" placeholder="SEED, ANGEL, SERIES-A, SERIES-B, SERIES-C, BRIDGE" />
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-checkbox code="entrepreneur.investmentRound.form.label.finalMode" path="finalMode"/>
	</jstl:if>
	<acme:form-textarea code="entrepreneur.investmentRound.form.label.description" path="description"/>
	<acme:form-money code="entrepreneur.investmentRound.form.label.amountMoney" path="amountMoney"/>
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.link" path="link"/>
	
	<acme:form-submit test="${command =='create'}" code="entrepreneur.investmentRound.button.create" action="/entrepreneur/investment-round/create" />
	
	<acme:form-submit test="${command != 'create' && activities > 0}" code="entrepreneur.investmentRound.button.list.activities" method="get"
		action="/entrepreneur/activity/list-investment?investmentId=${investmentId}" />

	<acme:form-submit test="${(command != 'create' && finalMode == false)}" code="entrepreneur.investmentRound.button.create.activities" method="get"
		action="/entrepreneur/activity/create?investmentId=${investmentId}" />
		
	<acme:form-submit test="${(command != 'create' && finalMode == false)}" code="entrepreneur.investmentRound.button.update" 
		action="/entrepreneur/investment-round/update" />
		
	<acme:form-submit test="${(command != 'create' && applications == 0)}" code="entrepreneur.investmentRound.button.delete" 
		action="/entrepreneur/investment-round/delete" />

	<acme:form-submit test="${command != 'create' && accountingRecords > 0}" code="entrepreneur.investmentRound.button.accountingRecord" method="get"
		action="/entrepreneur/accounting-record/list-investment?investmentId=${investmentId}"/>
	
	<acme:form-return code="entrepreneur.investmentRound.button.return"/>	
</acme:form>