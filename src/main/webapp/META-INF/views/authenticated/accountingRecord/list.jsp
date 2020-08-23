<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.accountingRecord.list.label.title" path="title" width="20%"/>
	<acme:list-column code="authenticated.accountingRecord.list.label.creation" path="creation" width="20%"/>
		
</acme:list>
