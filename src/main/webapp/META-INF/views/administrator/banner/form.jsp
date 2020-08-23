
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>

	<acme:form-hidden path="hasCreditCard" />
	<acme:form-url code="administrator.banner.form.label.picture" path="picture"/>
	<acme:form-textbox code="administrator.banner.form.label.slogan" path="slogan"/>
	<acme:form-url code="administrator.banner.form.label.targetUrl" path="targetURL"/>
	<acme:form-url code="administrator.banner.form.label.patron" path="patron.organisationName"/>
	<jstl:if test="${hasCreditCard==true}">
	<fieldset>
	<legend><acme:message code="administrator.banner.form.label.creditCard"/></legend>
	<acme:form-textbox code="administrator.banner.form.label.holderName" path="creditCard.holderName" placeholder="Lida melgar zapata"/>
	<acme:form-textbox code="administrator.banner.form.label.brandName" path="creditCard.brandName" placeholder="VISA, MASTERCARD, DINNERS, AMEX"/>
	<acme:form-textbox code="administrator.banner.form.label.number" path="creditCard.number" placeholder="1111222233334444"/>
	<acme:form-integer code="administrator.banner.form.label.expirationMonth" path="creditCard.expirationMonth" placeholder="02"/>
	<acme:form-integer code="administrator.banner.form.label.expirationYear" path="creditCard.expirationYear" placeholder="22"/>
	<acme:form-integer code="administrator.banner.form.label.cvv" path="creditCard.cvv" placeholder="987"/>
	</fieldset>
	</jstl:if>

	
	<acme:form-return code="administrator.banner.button.return"/>	
</acme:form>