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
	
		<acme:form-hidden path="hasCreditCard" />
		<acme:form-url code="patron.banner.form.label.picture" path="picture" />
		<acme:form-textbox code="patron.banner.form.label.slogan" path="slogan"/>
		<acme:form-url code="patron.banner.form.label.targetURL" path="targetURL"/>
		
		<jstl:if test="${(command=='show' || command=='update') && hasCreditCard==true}">
			<fieldset>
			<legend><acme:message code="patron.banner.form.label.creditCard"/></legend>
			<acme:form-textbox code="patron.banner.form.label.holderName" path="creditCard.holderName" readonly="true" />
			<acme:form-textbox code="patron.banner.form.label.brandName" path="creditCard.brandName" readonly="true"/>
			<acme:form-textbox code="patron.banner.form.label.number" path="creditCard.number" readonly="true"/>
			<acme:form-textbox code="patron.banner.form.label.expirationMonth" path="creditCard.expirationMonth" readonly="true"/>
			<acme:form-textbox code="patron.banner.form.label.expirationYear" path="creditCard.expirationYear" readonly="true"/>
			<acme:form-textbox code="patron.banner.form.label.cvv" path="creditCard.cvv" readonly="true"/>
			</fieldset>
		</jstl:if>
	
		<acme:form-submit test="${command == 'create'}" code="patron.banner.button.create" action="/patron/banner/create"/>
  		<acme:form-submit test="${command != 'create'}" code="patron.banner.button.update" action="/patron/banner/update"/>
  		<acme:form-submit test="${command != 'create'}" code="patron.banner.button.delete" action="/patron/banner/delete"/>
  		
  		<acme:form-return code="patron.banner.button.return"/>
  		
</acme:form>

