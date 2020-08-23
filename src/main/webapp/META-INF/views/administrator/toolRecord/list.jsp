<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.toolRecord.list.label.title" path="title" width="20%"/>
	<acme:list-column code="administrator.toolRecord.list.label.activitySector" path="activitySector" width="20%"/>
	<acme:list-column code="administrator.toolRecord.list.label.inventorName" path="inventorName" width="40%"/>	
	<acme:list-column code="administrator.toolRecord.list.label.stars" path="stars" width="20%"/>		
		
</acme:list>
