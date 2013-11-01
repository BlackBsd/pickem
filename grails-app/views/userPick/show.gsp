
<%@ page import="com.schlomp.pickem.UserPick" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'userPick.label', default: 'UserPick')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-userPick" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-userPick" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list userPick">
			
				<g:if test="${userPickInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="userPick.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${userPickInstance?.user?.id}">${userPickInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${userPickInstance?.matchUp}">
				<li class="fieldcontain">
					<span id="matchUp-label" class="property-label"><g:message code="userPick.matchUp.label" default="Match Up" /></span>
					
						<span class="property-value" aria-labelledby="matchUp-label"><g:link controller="proMatchUp" action="show" id="${userPickInstance?.matchUp?.id}">${userPickInstance?.matchUp?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${userPickInstance?.winner}">
				<li class="fieldcontain">
					<span id="winner-label" class="property-label"><g:message code="userPick.winner.label" default="Winner" /></span>
					
						<span class="property-value" aria-labelledby="winner-label"><g:link controller="proTeam" action="show" id="${userPickInstance?.winner?.id}">${userPickInstance?.winner?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${userPickInstance?.homeScore}">
				<li class="fieldcontain">
					<span id="homeScore-label" class="property-label"><g:message code="userPick.homeScore.label" default="Home Score" /></span>
					
						<span class="property-value" aria-labelledby="homeScore-label"><g:fieldValue bean="${userPickInstance}" field="homeScore"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userPickInstance?.awayScore}">
				<li class="fieldcontain">
					<span id="awayScore-label" class="property-label"><g:message code="userPick.awayScore.label" default="Away Score" /></span>
					
						<span class="property-value" aria-labelledby="awayScore-label"><g:fieldValue bean="${userPickInstance}" field="awayScore"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${userPickInstance?.id}" />
					<g:link class="edit" action="edit" id="${userPickInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
