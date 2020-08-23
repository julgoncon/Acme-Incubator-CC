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

<acme:form readonly="true">
	<acme:form-textbox code="administrator.bookkeeperRequest.form.label.firm" path="firmName"/>
	<acme:form-textbox code="administrator.bookkeeperRequest.form.label.responsibilityStatement" path="responsibilityStatement"/>
	<acme:form-textbox code="administrator.bookkeeperRequest.form.label.userAccount" path="userAccount.username"/>
	
		<acme:form-submit code="administrator.bookkeeperRequest.button.accept" method="post" 
		action="/administrator/bookkeeper-request/accept"/>
		
		<acme:form-submit code="administrator.bookkeeperRequest.button.reject" method="post" 
		action="/administrator/bookkeeper-request/reject"/>

  	<acme:form-return code="administrator.bookkeeperRequest.button.return"/>
</acme:form>





