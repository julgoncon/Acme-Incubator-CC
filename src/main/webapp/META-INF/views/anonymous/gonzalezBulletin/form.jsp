<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.gonzalezBulletin.form.label.author" path="author"/>
	<acme:form-textarea code="anonymous.gonzalezBulletin.form.label.text" path="text"/>
	
	<acme:form-submit code="anonymous.gonzalezBulletin.button.create" action="/anonymous/gonzalez-bulletin/create"/>
  	<acme:form-return code="anonymous.gonzalezBulletin.button.return"/>
</acme:form>