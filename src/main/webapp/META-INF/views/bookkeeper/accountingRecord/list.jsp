<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="bookkeeper.accountingRecord.list.label.title" path="title" width="33%"/>
	<acme:list-column code="bookkeeper.accountingRecord.list.label.creation" path="creation" width="33%"/>
	<acme:list-column code="bookkeeper.accountingRecord.list.label.investmentRound.ticker" path="investmentRound.ticker" width="33%"/>	
</acme:list>
