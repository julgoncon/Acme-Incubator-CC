<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="investor.activity.form.label.title" path="title"/>
	<acme:form-moment code="investor.activity.form.label.startMoment" path="startMoment"/>
	<acme:form-moment code="investor.activity.form.label.endMoment" path="endMoment"/>
	<acme:form-money code="investor.activity.form.label.budget" path="budget"/>


	<acme:form-return code="investor.activity.button.return"/>	
</acme:form>