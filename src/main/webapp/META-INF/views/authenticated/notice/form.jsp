<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.notice.form.label.picture" path="picture"/>
	<acme:form-textbox code="authenticated.notice.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.notice.form.label.creation" path="creation"/>
	<acme:form-moment code="authenticated.notice.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.notice.form.label.body" path="body"/>
	<acme:form-textarea code="authenticated.notice.form.label.links" path="links"/>

	<acme:form-return code="authenticated.notice.form.button.return"/>	
</acme:form>