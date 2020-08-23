<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<acme:form>
	<acme:form-hidden path="selected" />
	<acme:form-hidden path="activitySectors" />
	<acme:form-textbox code="administrator.technologyRecord.form.label.title" path="title"/>
	<acme:form-select code="administrator.technologyRecord.form.label.activitySector" path="activitySector">
	<c:forEach items="${activitySectors}" var="item">
	<option value="${item}" <jstl:if test="${selected == item}">selected</jstl:if>>
        <c:out value = "${item}"/>
    </option>
    
	</c:forEach>
	</acme:form-select>
	<acme:form-textbox code="administrator.technologyRecord.form.label.inventorName" path="inventorName"/>
	<acme:form-textarea code="administrator.technologyRecord.form.label.description" path="description"/>
	<acme:form-url code="administrator.technologyRecord.form.label.website" path="website"/>
	<acme:form-textbox code="administrator.technologyRecord.form.label.email" path="email"/>
	<acme:form-checkbox code="administrator.technologyRecord.form.label.openSource" path="openSource"/>
	<acme:form-double code="administrator.technologyRecord.form.label.stars" path="stars"/>
	
	<acme:form-submit test="${command=='show'}" 
	code="administrator.technology-record.form.button.update" 
	action="/administrator/technology-record/update"/>
	<acme:form-submit test="${command=='create'}" 
	code="administrator.technology-record.form.button.create" 
	action="/administrator/technology-record/create"/>
	<acme:form-submit test="${command=='update'}" 
	code="administrator.technology-record.form.button.update" 
	action="/administrator/technology-record/update"/>
	<acme:form-submit test="${command!='create'}" 
	code="administrator.technology-record.form.button.delete" 
	action="/administrator/technology-record/delete"/>
	
	<acme:form-return code="administrator.technologyRecord.button.return"/>	
</acme:form>