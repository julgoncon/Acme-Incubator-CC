<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="bookkeeper.investmentRound.list.label.ticker" path="ticker" width="20%"/>
	<acme:list-column code="bookkeeper.investmentRound.list.label.title" path="title" width="20%"/>
	<acme:list-column code="bookkeeper.investmentRound.list.label.creation" path="creation" width="20%"/>
	<acme:list-column code="bookkeeper.investmentRound.list.label.kindRound" path="kindRound" width="20%"/>
	<acme:list-column code="bookkeeper.investmentRound.list.label.amountMoney" path="amountMoney" width="20%"/>		
</acme:list>
