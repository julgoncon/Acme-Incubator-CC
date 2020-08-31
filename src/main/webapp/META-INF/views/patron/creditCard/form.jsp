<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@page language="java" import="acme.framework.helpers.MessageHelper"%>

<acme:form>

	<acme:form-textbox code="patron.creditCard.label.holderName" path="holderName" />
	<acme:form-textbox code="patron.creditCard.label.brandName" path="brandName" placeholder="VISA|MASTERCARD|DINNERS|AMEX"/>
	<acme:form-textbox code="patron.creditCard.label.number" path="number" placeholder="1111222233334444"/>
	<acme:form-integer code="patron.creditCard.label.expirationMonth" path="expirationMonth" placeholder="MM"/>
	<acme:form-integer code="patron.creditCard.label.expirationYear" path="expirationYear" placeholder="YY"/>
	<acme:form-integer code="patron.creditCard.label.cvv" path="cvv" placeholder="NNN"/>
	
	<acme:form-submit test="${command == 'create'}" code="patron.creditCard.button.create" action="/patron/credit-card/create"/>
  	<acme:form-submit test="${command != 'create'}" code="patron.creditCard.button.update" action="/patron/credit-card/update"/>
  	<acme:form-return code="patron.creditCard.button.return"/>
  		
</acme:form>

