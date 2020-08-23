<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.forum.list.label.title" path="title" width="30%"/>
	<acme:list-column code="authenticated.forum.list.label.investment.title" path="investmentRound.title" width="30%"/>
		
</acme:list>
