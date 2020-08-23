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

	<acme:form-textarea code="administrator.customisationParameter.form.label.spamWords" path="spamWords" 
		placeholder="${MessageHelper.getMessage('administrator.customisationParameter.form.placeholder.spamWords')}"/>
	<acme:form-textarea code="administrator.customisationParameter.form.label.activitySector" path="activitySector" 
		placeholder="${MessageHelper.getMessage('administrator.customisationParameter.form.placeholder.activitySector')}"/>
	<acme:form-double code="administrator.customisationParameter.form.label.spamThreshold" path="spamThreshold" 
		placeholder="${MessageHelper.getMessage('administrator.customisationParameter.form.placeholder.spamThreshold')}"/>
	<acme:form-submit code="administrator.customisationParameter.form.button.update" 
		action="/administrator/customisation-parameter/update"/>
  	<acme:form-return code="administrator.customisationParameter.form.button.return"/>
  	
</acme:form>
