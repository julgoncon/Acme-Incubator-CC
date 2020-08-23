<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<acme:form>
	<acme:form-textbox code="authenticated.toolRecord.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.toolRecord.form.label.activitySector" path="activitySector"/>
	<acme:form-textbox code="authenticated.toolRecord.form.label.inventorName" path="inventorName"/>
	<acme:form-textarea code="authenticated.toolRecord.form.label.description" path="description"/>
	<acme:form-url code="authenticated.toolRecord.form.label.website" path="website"/>
	<acme:form-textarea code="authenticated.toolRecord.form.label.email" path="email"/>
	<acme:form-checkbox code="authenticated.toolRecord.form.label.openSource" path="openSource" readonly="true"/>
	<acme:form-double code="authenticated.toolRecord.form.label.stars" path="stars"/>
	
	<acme:form-return code="authenticated.toolRecord.button.return"/>	
</acme:form>