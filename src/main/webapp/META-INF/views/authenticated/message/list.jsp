<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.message.list.label.title" path="title" width="33%"/>
	<acme:list-column code="authenticated.message.list.label.creation" path="creation" width="33%"/>
	<acme:list-column code="authenticated.message.list.label.username" path="authenticated.userAccount.username" width="33%"/>
</acme:list>
