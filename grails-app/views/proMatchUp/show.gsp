
<%@ page import="com.schlomp.pickem.ProMatchUp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'proMatchUp.label', default: 'ProMatchUp')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-proMatchUp" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-proMatchUp" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list proMatchUp">
			
				<g:if test="${proMatchUpInstance?.homeTeam}">
				<li class="fieldcontain">
					<span id="homeTeam-label" class="property-label"><g:message code="proMatchUp.homeTeam.label" default="Home Team" /></span>
					
						<span class="property-value" aria-labelledby="homeTeam-label"><g:link controller="proTeam" action="show" id="${proMatchUpInstance?.homeTeam?.id}">${proMatchUpInstance?.homeTeam?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${proMatchUpInstance?.awayTeam}">
				<li class="fieldcontain">
					<span id="awayTeam-label" class="property-label"><g:message code="proMatchUp.awayTeam.label" default="Away Team" /></span>
					
						<span class="property-value" aria-labelledby="awayTeam-label"><g:link controller="proTeam" action="show" id="${proMatchUpInstance?.awayTeam?.id}">${proMatchUpInstance?.awayTeam?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${proMatchUpInstance?.isTieBreaker}">
				<li class="fieldcontain">
					<span id="isTieBreaker-label" class="property-label"><g:message code="proMatchUp.isTieBreaker.label" default="Is Tie Breaker" /></span>
					
						<span class="property-value" aria-labelledby="isTieBreaker-label"><g:formatBoolean boolean="${proMatchUpInstance?.isTieBreaker}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${proMatchUpInstance?.gameTime}">
				<li class="fieldcontain">
					<span id="gameTime-label" class="property-label"><g:message code="proMatchUp.gameTime.label" default="Game Time" /></span>
					
						<span class="property-value" aria-labelledby="gameTime-label"><g:fieldValue bean="${proMatchUpInstance}" field="gameTime"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${proMatchUpInstance?.winningTeam}">
				<li class="fieldcontain">
					<span id="winningTeam-label" class="property-label"><g:message code="proMatchUp.winningTeam.label" default="Winning Team" /></span>
					
						<span class="property-value" aria-labelledby="winningTeam-label"><g:link controller="proTeam" action="show" id="${proMatchUpInstance?.winningTeam?.id}">${proMatchUpInstance?.winningTeam?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${proMatchUpInstance?.homeScore}">
				<li class="fieldcontain">
					<span id="homeScore-label" class="property-label"><g:message code="proMatchUp.homeScore.label" default="Home Score" /></span>
					
						<span class="property-value" aria-labelledby="homeScore-label"><g:fieldValue bean="${proMatchUpInstance}" field="homeScore"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${proMatchUpInstance?.awayScore}">
				<li class="fieldcontain">
					<span id="awayScore-label" class="property-label"><g:message code="proMatchUp.awayScore.label" default="Away Score" /></span>
					
						<span class="property-value" aria-labelledby="awayScore-label"><g:fieldValue bean="${proMatchUpInstance}" field="awayScore"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${proMatchUpInstance?.id}" />
					<g:link class="edit" action="edit" id="${proMatchUpInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
