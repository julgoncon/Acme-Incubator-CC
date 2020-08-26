<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="entrepreneur.application.list.label.ticker" path="ticker" width="20%"/>
	<acme:list-column code="entrepreneur.application.list.label.investmentRound" path="investmentRound.title" width="20%"/>
	<acme:list-column code="entrepreneur.application.list.label.investmentRound.ticker" path="investmentRound.ticker" width="20%"/>
	<acme:list-column code="entrepreneur.application.list.label.status" path="status" width="20%"/>
	<acme:list-column code="entrepreneur.application.list.label.creation" path="creation" width="20%"/>
	<acme:list-column code="entrepreneur.application.list.label.investmentOffer" path="investmentOffer" width="20%"/>
		
</acme:list>
