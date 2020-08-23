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
<%@page language="java" import="acme.framework.helpers.MessageHelper"%>

<acme:form>
<acme:form-hidden path="investmentId"/>
	<acme:form-textarea code="entrepreneur.demand.form.label.text" path="text"/>
	
	<acme:form-submit test="${command =='create'}" code="entrepreneur.demand.form.button.create"
		action="/entrepreneur/demand/create?investmentId=${investmentId}"/>
	<acme:form-submit test="${command =='show' || command=='update'}" code="entrepreneur.demand.form.button.update"
		action="/entrepreneur/demand/update?investmentId=${investmentId}"/>
	<acme:form-submit test="${command =='show' || command=='update'}" code="entrepreneur.demand.form.button.delete"
		action="/entrepreneur/demand/delete?investmentId=${investmentId}"/>

  	<acme:form-return code="entrepreneur.demand.form.button.return"/>
</acme:form>





