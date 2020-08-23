<%--
- list.jsp
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

<acme:list>
	<acme:list-column code="administrator.customisationParameter.list.label.spamWords"  path="spamWords" width="45%"/>
	<acme:list-column code="administrator.customisationParameter.list.label.activitySector"  path="activitySector" width="45%"/>
	<acme:list-column code="administrator.customisationParameter.list.label.spamThreshold" path="spamThreshold" width="10%"/>
</acme:list>
