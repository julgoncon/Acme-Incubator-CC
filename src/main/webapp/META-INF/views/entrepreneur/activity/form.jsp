<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="finalMode"/>
	<acme:form-textbox code="entrepreneur.activity.form.label.title" path="title"/>
	<acme:form-moment code="entrepreneur.activity.form.label.startMoment" path="startMoment"/>
	<acme:form-moment code="entrepreneur.activity.form.label.endMoment" path="endMoment"/>
	<acme:form-money code="entrepreneur.activity.form.label.budget" path="budget"/>

	<acme:form-submit test="${(command == 'create')}" code="entrepreneur.activity.button.create"
		action="/entrepreneur/activity/create?investmentId=${investmentId}" />
	
	<acme:form-submit test="${(command != 'create' && finalMode==false)}" code="entrepreneur.activity.button.update"
		action="/entrepreneur/activity/update" />
	
	<acme:form-submit test="${(command != 'create' && finalMode==false)}" code="entrepreneur.activity.button.delete"
		action="/entrepreneur/activity/delete" />

	<acme:form-return code="entrepreneur.activity.button.return"/>	
</acme:form>