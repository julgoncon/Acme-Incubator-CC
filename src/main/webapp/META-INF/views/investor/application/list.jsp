<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="investor.application.list.label.ticker" path="ticker" width="33%"/>
	<acme:list-column code="investor.application.list.label.status" path="status" width="33%"/>
	<acme:list-column code="investor.application.list.label.investmentOffer" path="investmentOffer" width="33%"/>
		
</acme:list>
