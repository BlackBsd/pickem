
<%@ page import="com.schlomp.pickem.ProTeam" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'proTeam.label', default: 'ProTeam')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-proTeam" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-proTeam" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'proTeam.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="location" title="${message(code: 'proTeam.location.label', default: 'Location')}" />
					
						<g:sortableColumn property="conference" title="${message(code: 'proTeam.conference.label', default: 'Conference')}" />
					
						<g:sortableColumn property="division" title="${message(code: 'proTeam.division.label', default: 'Division')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${proTeamInstanceList}" status="i" var="proTeamInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${proTeamInstance.id}">${fieldValue(bean: proTeamInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: proTeamInstance, field: "location")}</td>
					
						<td>${fieldValue(bean: proTeamInstance, field: "conference")}</td>
					
						<td>${fieldValue(bean: proTeamInstance, field: "division")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${proTeamInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
