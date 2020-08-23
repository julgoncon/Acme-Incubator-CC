<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	
	<acme:form-hidden path="forumId" />
	<acme:form-hidden path="hasMsgs" />
	<acme:form-hidden path="isCreator" />
	<acme:form-textbox code="authenticated.forum.form.label.title" path="title"/>
	
	<acme:form-submit test="${hasMsgs==true}" code="authenticated.forum.button.messages" method="get"
		action="/authenticated/message/list?forumId=${forumId}" />
		
	<acme:form-submit test="${command != 'create' && isCreator==true}" code="authenticated.forum.button.forumUser.list" method="get" 
	action="/authenticated/forum-user/list?forumId=${forumId}"/>
	
	<acme:form-submit test="${command != 'create' && isCreator==true}" code="authenticated.forum.button.forumUser.add" method="get" 
	action="/authenticated/forum-user/create?forumId=${forumId}"/>
	
	<acme:form-submit test="${command != 'create'}" code="authenticated.forum.button.create.messages" method="get" 
			action="/authenticated/message/create?forumId=${forumId}"/>
		
	<acme:form-submit test="${command == 'create'}" code="authenticated.forum.button.create" 
	action="/authenticated/forum/create"/>
	
	<acme:form-submit test="${command != 'create' && isCreator==true}" code="authenticated.forum.button.delete" 
	action="/authenticated/forum/delete"/>
	
	<acme:form-return code="authenticated.forum.button.return"/>	
</acme:form>