<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="forumId" />
	<acme:form-textbox code="authenticated.message.form.label.title" path="title"/>
	<jstl:if test="${command == 'show'}">
	<acme:form-moment code="authenticated.message.form.label.creation" path="creation"/>
	<acme:form-textbox code="authenticated.message.form.label.username" path="authenticated.userAccount.username"/>
	
	</jstl:if>
	
	<acme:form-textbox code="authenticated.message.form.label.tags" path="tags"/>
	<acme:form-textarea code="authenticated.message.form.label.body" path="body"/>
	
	<jstl:if test="${command == 'create'}">
	<acme:form-checkbox code="authenticated.message.button.confirm" path="confirm"/>
	</jstl:if>
	<acme:form-submit  test="${command == 'create'}" code="authenticated.message.button.create" 
			action="/authenticated/message/create?forumId=${forumId}"/>

	<acme:form-return code="authenticated.message.button.return"/>	
</acme:form>