<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="investmentId" />
	<acme:form-hidden path="canUpdate" />
	<acme:form-textbox code="bookkeeper.accountingRecord.form.label.title" path="title"/>
	<jstl:if test="${command == 'show' || command == 'update'}">
	<acme:form-moment code="bookkeeper.accountingRecord.form.label.creation" path="creation" readonly="true"/>
	</jstl:if>
	<acme:form-checkbox code="bookkeeper.accountingRecord.form.label.draft" path="draft"/>
	<acme:form-textarea code="bookkeeper.accountingRecord.form.label.body" path="body"/>

	<acme:form-submit test="${command == 'create'}" code="bookkeeper.accountingRecord.button.create" 
	action="/bookkeeper/accounting-record/create?investmentId=${investmentId}"/>
	
	<acme:form-submit test="${command != 'create' && canUpdate==true}" code="bookkeeper.accountingRecord.button.update" 
	action="/bookkeeper/accounting-record/update"/>

	<acme:form-return code="bookkeeper.accountingRecord.button.return"/>	
</acme:form>