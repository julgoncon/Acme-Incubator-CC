<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.accountingRecord.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.accountingRecord.form.label.creation" path="creation"/>
	<acme:form-checkbox code="authenticated.accountingRecord.form.label.draft" path="draft"/>
	<acme:form-textarea code="authenticated.accountingRecord.form.label.body" path="body"/>


	<acme:form-return code="authenticated.accountingRecord.button.return"/>	
</acme:form>