<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@page language="java" import="acme.framework.helpers.MessageHelper"%>

<acme:form>

	<acme:form-hidden path="investmentId" />
	<acme:form-hidden path="finalMode" />
	<acme:form-textbox code="investor.investmentRound.form.label.ticker" path="ticker" placeholder="SSS-YY-NNNNNN"/>
	<acme:form-moment code="investor.investmentRound.form.label.creation" path="creation"/>
	<acme:form-textbox code="investor.investmentRound.form.label.kindRound" path="kindRound" placeholder="SEED, ANGEL, SERIES-A, SERIES-B, SERIES-C, BRIDGE" />
	<acme:form-textbox code="investor.investmentRound.form.label.title" path="title"/>
	<acme:form-checkbox code="investor.investmentRound.form.label.finalMode" path="finalMode"/>
	<acme:form-textarea code="investor.investmentRound.form.label.description" path="description"/>
	<acme:form-money code="investor.investmentRound.form.label.amountMoney" path="amountMoney"/>
	<acme:form-textbox code="investor.investmentRound.form.label.link" path="link"/>
	<acme:form-textbox code="investor.investmentRound.form.label.text" path="text"/>

	<acme:form-submit test="${finalMode == true}" code="investor.investmentRound.button.create.application" method="get"
		action="/investor/application/create?investmentId=${investmentId}" />
	
	<acme:form-return code="investor.investmentRound.button.return"/>	
</acme:form>