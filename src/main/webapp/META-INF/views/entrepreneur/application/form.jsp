<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="investmentId" />
	<acme:form-textbox code="entrepreneur.application.form.label.ticker" path="ticker" readonly="true"/>
	<acme:form-moment code="entrepreneur.application.form.label.creation" path="creation"  readonly="true"/>
	<jstl:if test="${command== 'show' && status== 'accepted' || command== 'show' && status== 'rejected' || isJustificated==true}">
		<acme:form-textbox code="entrepreneur.application.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${command== 'update' && status== 'pending' || command== 'show' && status== 'pending'}">
		<acme:form-select code="entrepreneur.application.form.label.status" path="status">
		<acme:form-option code="entrepreneur.application.form.label.status.rejected" value="rejected"/>
		<acme:form-option code="entrepreneur.application.form.label.status.accepted" value="accepted" />
		<acme:form-option selected="true" code="entrepreneur.application.form.label.status.pending" value="pending"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${status == 'rejected' && command== 'update'}">
		<acme:form-select code="entrepreneur.application.form.label.status" path="status">
		<acme:form-option selected="true" code="entrepreneur.application.form.label.status.rejected" value="rejected"/>
		<acme:form-option code="entrepreneur.application.form.label.status.accepted" value="accepted" />
		<acme:form-option code="entrepreneur.application.form.label.status.pending" value="pending"/>
	</acme:form-select>
	</jstl:if>
	
	<acme:form-textbox code="entrepreneur.application.form.label.justification" path="justification"/>
	<acme:form-textarea code="entrepreneur.application.form.label.statement" path="statement"  readonly="true"/>
	<acme:form-money code="entrepreneur.application.form.label.investmentOffer" path="investmentOffer"  readonly="true"/>

	<acme:form-textarea code="entrepreneur.application.form.label.offer" path="offer" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.moreInfo" path="moreInfo" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.password" path="password" readonly="true"/>
	
	<acme:form-submit test ="${command == 'update' || command == 'show' && status == 'pending'}" code="entrepreneur.application.button.update" action="/entrepreneur/application/update"/>
	<acme:form-submit code="entrepreneur.investmentRound.button" method="get"
		action="/entrepreneur/investment-round/show?id=${investmentId}"/>
	<acme:form-return code="entrepreneur.application.button.return"/>	
</acme:form>