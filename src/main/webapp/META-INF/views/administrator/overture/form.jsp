<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.overture.form.label.title" path="title"/>
	<jstl:if test="${command!='create'}">
		<acme:form-moment code="administrator.overture.form.label.creation" path="creation" readonly="true"/>
	</jstl:if>
	<acme:form-moment code="administrator.overture.form.label.deadline" path="deadline" />
	<acme:form-textarea code="administrator.overture.form.label.paragraphs" path="paragraphs"/>
	<acme:form-money code="administrator.overture.form.label.minMoney" path="minMoney"/>
	<acme:form-money code="administrator.overture.form.label.maxMoney" path="maxMoney"/>
	<acme:form-textbox code="administrator.overture.form.label.email" path="email"/>
	
	<acme:form-submit test="${command=='show'}" 
	code="administrator.overture.form.button.update" 
	action="/administrator/overture/update"/>
	<acme:form-submit test="${command=='create'}" 
	code="administrator.overture.form.button.create" 
	action="/administrator/overture/create"/>
	<acme:form-submit test="${command=='update'}" 
	code="administrator.overture.form.button.update" 
	action="/administrator/overture/update"/>
	<acme:form-submit test="${command!='create'}" 
	code="administrator.overture.form.button.delete" 
	action="/administrator/overture/delete"/>

	<acme:form-return code="administrator.overture.button.return"/>	
</acme:form>