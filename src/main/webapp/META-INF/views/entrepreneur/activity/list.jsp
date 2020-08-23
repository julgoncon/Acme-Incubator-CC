<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="entrepreneur.activity.list.label.title" path="title" width="50%"/>
	<acme:list-column code="entrepreneur.activity.list.label.budget" path="budget" width="50%"/>
		
</acme:list>
