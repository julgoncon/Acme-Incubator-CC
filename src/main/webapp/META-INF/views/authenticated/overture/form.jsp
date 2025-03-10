<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.overture.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.overture.form.label.creation" path="creation"/>
	<acme:form-moment code="authenticated.overture.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.overture.form.label.paragraphs" path="paragraphs"/>
	<acme:form-money code="authenticated.overture.form.label.minMoney" path="minMoney"/>
	<acme:form-money code="authenticated.overture.form.label.maxMoney" path="maxMoney"/>
	<acme:form-textbox code="authenticated.overture.form.label.email" path="email"/>

	<acme:form-return code="authenticated.overture.button.return"/>	
</acme:form>