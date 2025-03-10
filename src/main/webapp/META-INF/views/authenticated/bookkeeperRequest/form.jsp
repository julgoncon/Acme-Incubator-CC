<%--
- jsp
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
	<acme:form-textbox code="authenticated.bookkeeperRequest.form.label.firm" path="firmName"/>
	<acme:form-textarea code="authenticated.bookkeeperRequest.form.label.responsibilityStatement" path="responsibilityStatement"/>

	
		<acme:form-submit code="authenticated.bookkeeperRequest.button.create" 
		action="/authenticated/bookkeeper-request/create"/>

  	<acme:form-return code="authenticated.bookkeeperRequest.button.return"/>
</acme:form>





