
<%@ page import="com.schlomp.pickem.ProWeek" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'proWeek.label', default: 'ProWeek')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-proWeek" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-proWeek" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list proWeek">
			
				<g:if test="${proWeekInstance?.weekNumber}">
				<li class="fieldcontain">
					<span id="weekNumber-label" class="property-label"><g:message code="proWeek.weekNumber.label" default="Week Number" /></span>
					
						<span class="property-value" aria-labelledby="weekNumber-label"><g:fieldValue bean="${proWeekInstance}" field="weekNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${proWeekInstance?.proMatchUps}">
				<li class="fieldcontain">
					<span id="proMatchUps-label" class="property-label"><g:message code="proWeek.proMatchUps.label" default="Pro Match Ups" /></span>
					
						<g:each in="${proWeekInstance.proMatchUps}" var="p">
						<span class="property-value" aria-labelledby="proMatchUps-label"><g:link controller="proMatchUp" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${proWeekInstance?.byeTeams}">
				<li class="fieldcontain">
					<span id="byeTeams-label" class="property-label"><g:message code="proWeek.byeTeams.label" default="Bye Teams" /></span>
					
						<g:each in="${proWeekInstance.byeTeams}" var="b">
						<span class="property-value" aria-labelledby="byeTeams-label"><g:link controller="proTeam" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${proWeekInstance?.id}" />
					<g:link class="edit" action="edit" id="${proWeekInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
