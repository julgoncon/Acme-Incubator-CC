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

<%@page language="java" import= "acme.framework.helpers.MessageHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.challenge.form.label.title"  path="title"/>
	<acme:form-textarea code="administrator.challenge.form.label.description"  path="description"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="administrator.challenge.form.label.deadline"  path="deadline" readonly="true"/>
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:form-moment code="administrator.challenge.form.label.deadline"  path="deadline"/>
	</jstl:if>
	<acme:form-textbox code="administrator.challenge.form.label.rookieLevelGoal"  path="rookieLevelGoal"/>
	<acme:form-money code="administrator.challenge.form.label.rookieLevelReward"  path="rookieLevelReward"/>
	<acme:form-textbox code="administrator.challenge.form.label.averageLevelGoal"  path="averageLevelGoal"/>
	<acme:form-money code="administrator.challenge.form.label.averageLevelReward"  path="averageLevelReward"/>
	<acme:form-textbox code="administrator.challenge.form.label.expertLevelGoal"  path="expertLevelGoal"/>
	<acme:form-money code="administrator.challenge.form.label.expertLevelReward"  path="expertLevelReward"/>
	
	<acme:form-submit test="${command == 'create' }"
		code="administrator.challenge.form.button.create"
		action= "/administrator/challenge/create"/>
	<acme:form-submit test="${command != 'create' }"
		code="administrator.challenge.form.button.update"
		action= "/administrator/challenge/update"/>
	<acme:form-submit test="${command != 'create' }"
		code="administrator.challenge.form.button.delete"
		action= "/administrator/challenge/delete"/>
	
  	<acme:form-return code="administrator.challenge.form.button.return"/>
</acme:form>
