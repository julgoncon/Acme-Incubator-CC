<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.notice.form.label.picture" path="picture"/>
	<acme:form-textbox code="administrator.notice.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment 
			code="administrator.notice.form.label.creation" 
			path="creation"
			readonly="true"/>
	</jstl:if>
	<acme:form-moment code="administrator.notice.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.notice.form.label.body" path="body"/>
	<acme:form-textarea code="administrator.notice.form.label.links" path="links"/>
	<jstl:if test="${command == 'create'}">
	<acme:form-checkbox code="administrator.notice.form.label.accept" path="accept"/>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}"
	code="administrator.notice.button.create" 
	action="/administrator/notice/create"/>
	
	<acme:form-return code="administrator.notice.button.return"/>	
</acme:form>