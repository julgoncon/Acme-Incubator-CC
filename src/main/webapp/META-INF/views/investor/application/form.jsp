<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@page language="java" import="acme.framework.helpers.MessageHelper"%>
<acme:form>

	<acme:form-hidden path="investmentId" />
	<jstl:if test="${command == 'create'}">
		<acme:form-textbox code="investor.application.form.label.ticker" path="ticker"  placeholder="SSS-YY-NNNNNN"/>
		<jstl:out value="${MessageHelper.getMessage('investor.application.form.label.tickerExplanation')}"/>
		<acme:form-textarea code="investor.application.form.label.statement" path="statement"/>
		<acme:form-money code="investor.application.form.label.investmentOffer" path="investmentOffer"/>
	</jstl:if>
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="investor.application.form.label.ticker" path="ticker"/>
		<acme:form-moment code="investor.application.form.label.creation" path="creation"/>
		<acme:form-textbox code="investor.application.form.label.status" path="status"/>
		<acme:form-textarea code="investor.application.form.label.statement" path="statement"/>
		<acme:form-money code="investor.application.form.label.investmentOffer" path="investmentOffer"/>
	</jstl:if>
		
	

	<acme:form-submit test="${command == 'create'}" code="investor.application.button.create"
		action="/investor/application/create?investmentId=${investmentId}" />

	<acme:form-return code="investor.application.button.return"/>	
</acme:form>