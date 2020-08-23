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

<acme:form readonly="true">

	<acme:form-textbox code="authenticated.challenge.form.label.title"  path="title"/>
	<acme:form-textarea code="authenticated.challenge.form.label.description"  path="description"/>
	<acme:form-moment code="authenticated.challenge.form.label.deadline"  path="deadline"/>
	<acme:form-textbox code="authenticated.challenge.form.label.rookieLevelGoal"  path="rookieLevelGoal"/>
	<acme:form-money code="authenticated.challenge.form.label.rookieLevelReward"  path="rookieLevelReward"/>
	<acme:form-textbox code="authenticated.challenge.form.label.averageLevelGoal"  path="averageLevelGoal"/>
	<acme:form-money code="authenticated.challenge.form.label.averageLevelReward"  path="averageLevelReward"/>
	<acme:form-textbox code="authenticated.challenge.form.label.expertLevelGoal"  path="expertLevelGoal"/>
	<acme:form-money code="authenticated.challenge.form.label.expertLevelReward"  path="expertLevelReward"/>
	
  	<acme:form-return code="authenticated.challenge.button.return"/>
  	
</acme:form>
