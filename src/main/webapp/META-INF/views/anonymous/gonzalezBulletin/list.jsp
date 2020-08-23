<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.gonzalezBulletin.list.label.author" path="author" width="20%"/>
	<acme:list-column code="anonymous.gonzalezBulletin.list.label.text" path="text" width="20%"/>
	<acme:list-column code="anonymous.gonzalezBulletin.list.label.moment" path="moment" width="60%"/>		
</acme:list>
