<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="investor.application.list.label.ticker" path="ticker" width="25%"/>
	<acme:list-column code="investor.application.list.label.status" path="status" width="25%"/>
	<acme:list-column code="investor.application.list.label.investmentOffer" path="investmentOffer" width="25%"/>
	<acme:list-column code="investor.application.list.label.investmentRound.ticker" path="investmentRound.ticker" width="25%"/>
</acme:list>
