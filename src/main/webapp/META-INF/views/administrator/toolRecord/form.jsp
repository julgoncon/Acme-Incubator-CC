<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<acme:form>
	<acme:form-hidden path="selected" />
	<acme:form-hidden path="activitySectors" />
	<acme:form-textbox code="administrator.toolRecord.form.label.title" path="title"/>
	<acme:form-select code="administrator.toolRecord.form.label.activitySector" path="activitySector">
	<c:forEach items="${activitySectors}" var="item">
	<option value="${item}" <jstl:if test="${selected == item}">selected</jstl:if>>
        <c:out value = "${item}"/>
    </option>
    
	</c:forEach>
	</acme:form-select>
	<acme:form-textbox code="administrator.toolRecord.form.label.inventorName" path="inventorName"/>
	<acme:form-textarea code="administrator.toolRecord.form.label.description" path="description"/>
	<acme:form-url code="administrator.toolRecord.form.label.website" path="website"/>
	<acme:form-textbox code="administrator.toolRecord.form.label.email" path="email"/>
	<acme:form-checkbox code="administrator.toolRecord.form.label.openSource" path="openSource"/>
	<acme:form-double code="administrator.toolRecord.form.label.stars" path="stars"/>
	
	<acme:form-submit test="${command=='show'}" 
	code="administrator.tool-record.form.button.update" 
	action="/administrator/tool-record/update"/>
	<acme:form-submit test="${command=='create'}" 
	code="administrator.tool-record.form.button.create" 
	action="/administrator/tool-record/create"/>
	<acme:form-submit test="${command=='update'}" 
	code="administrator.tool-record.form.button.update" 
	action="/administrator/tool-record/update"/>
	<acme:form-submit test="${command!='create'}" 
	code="administrator.tool-record.form.button.delete" 
	action="/administrator/tool-record/delete"/>
	
	<acme:form-return code="administrator.toolRecord.button.return"/>	
</acme:form>