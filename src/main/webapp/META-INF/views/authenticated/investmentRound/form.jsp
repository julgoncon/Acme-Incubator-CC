<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="accountingRecords" />
	<acme:form-textbox code="authenticated.investmentRound.form.label.ticker" path="ticker"/>
	<acme:form-moment code="authenticated.investmentRound.form.label.creation" path="creation"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.kindRound" path="kindRound"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.title" path="title"/>
	<acme:form-checkbox code="authenticated.investmentRound.form.label.finalMode" path="finalMode"/>
	<acme:form-textarea code="authenticated.investmentRound.form.label.description" path="description"/>
	<acme:form-money code="authenticated.investmentRound.form.label.amountMoney" path="amountMoney"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.link" path="link"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.startupName" path="entrepreneur.startupName"/>

	<acme:form-submit test="${accountingRecords > 0}" code="authenticated.investmentRound.button.accountingRecord" method="get"
		action="/authenticated/accounting-record/list-investment?investmentId=${investmentId}"/>

	<acme:form-return code="authenticated.investmentRound.button.return"/>	
</acme:form>