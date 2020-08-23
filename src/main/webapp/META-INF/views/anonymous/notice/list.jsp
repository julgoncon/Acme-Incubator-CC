<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.notice.list.label.creation" path="creation" width="20%"/>
	<acme:list-column code="anonymous.notice.list.label.body" path="body" width="20%"/>
	<acme:list-column code="anonymous.notice.list.label.links" path="links" width="60%"/>		
</acme:list>
