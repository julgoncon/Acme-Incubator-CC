<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="activities" />
	<acme:form-hidden path="accountingRecords" />
	<acme:form-textbox code="bookkeeper.investmentRound.form.label.ticker" path="ticker"/>
	<acme:form-moment code="bookkeeper.investmentRound.form.label.creation" path="creation"/>
	<acme:form-textbox code="bookkeeper.investmentRound.form.label.kindRound" path="kindRound"/>
	<acme:form-textbox code="bookkeeper.investmentRound.form.label.title" path="title"/>
	<acme:form-checkbox code="bookkeeper.investmentRound.form.label.finalMode" path="finalMode"/>
	<acme:form-textarea code="bookkeeper.investmentRound.form.label.description" path="description"/>
	<acme:form-money code="bookkeeper.investmentRound.form.label.amountMoney" path="amountMoney"/>
	<acme:form-textbox code="bookkeeper.investmentRound.form.label.link" path="link"/>
	<acme:form-textbox code="bookkeeper.investmentRound.form.label.startupName" path="entrepreneur.startupName"/>
	
	<acme:form-submit test="${accountingRecords > 0}" code="bookkeeper.investmentRound.button.accountingRecord" method="get"
		action="/bookkeeper/accounting-record/list-investment?investmentId=${investmentId}"/>
		
	<acme:form-submit test="${activities > 0}" code="bookkeeper.investmentRound.button.list.activities" method="get"
		action="/bookkeeper/activity/list-investment?investmentId=${investmentId}" />
	
	<acme:form-submit code="bookkeeper.investmentRound.button.accountingRecordCreate" method="get"
		action="/bookkeeper/accounting-record/create?investmentId=${investmentId}"/>

	<acme:form-return code="bookkeeper.investmentRound.button.return"/>	
</acme:form>