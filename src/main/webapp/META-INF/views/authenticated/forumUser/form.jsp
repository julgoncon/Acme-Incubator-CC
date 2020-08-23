<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="forumId"/>
	
	<jstl:if test="${command != 'create' }">
		<acme:form-textbox readonly="true"
			code="authenticated.forumUser.form.label.username" path="authenticated.userAccount.username"/>
	</jstl:if>
	<jstl:if test="${command == 'create' }">
		<acme:form-textbox code="authenticated.forumUser.form.label.username" path="authenticated.userAccount.username"/>
	</jstl:if>
	
	<jstl:if test="${idPrincipal == idCreator}">
		<acme:form-submit test="${command != 'create'}" code="authenticated.forumUser.button.delete" 
			action="/authenticated/forum-user/delete"/>
	</jstl:if>
		
	<acme:form-submit test="${command == 'create'}" code="authenticated.forumUser.button.create" 
		action="/authenticated/forum-user/create?forumId=${forumId}"/>
	 
  	<acme:form-return code="authenticated.forumUser.button.return"/>
</acme:form>





