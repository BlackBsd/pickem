
<%@ page import="com.schlomp.pickem.ProMatchUp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'proMatchUp.label', default: 'ProMatchUp')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-proMatchUp" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-proMatchUp" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="proMatchUp.homeTeam.label" default="Home Team" /></th>
					
						<th><g:message code="proMatchUp.awayTeam.label" default="Away Team" /></th>
					
						<g:sortableColumn property="isTieBreaker" title="${message(code: 'proMatchUp.isTieBreaker.label', default: 'Is Tie Breaker')}" />
					
						<g:sortableColumn property="gameTime" title="${message(code: 'proMatchUp.gameTime.label', default: 'Game Time')}" />
					
						<th><g:message code="proMatchUp.winningTeam.label" default="Winning Team" /></th>
					
						<g:sortableColumn property="homeScore" title="${message(code: 'proMatchUp.homeScore.label', default: 'Home Score')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${proMatchUpInstanceList}" status="i" var="proMatchUpInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${proMatchUpInstance.id}">${fieldValue(bean: proMatchUpInstance, field: "homeTeam")}</g:link></td>
					
						<td>${fieldValue(bean: proMatchUpInstance, field: "awayTeam")}</td>
					
						<td><g:formatBoolean boolean="${proMatchUpInstance.isTieBreaker}" /></td>
					
						<td>${fieldValue(bean: proMatchUpInstance, field: "gameTime")}</td>
					
						<td>${fieldValue(bean: proMatchUpInstance, field: "winningTeam")}</td>
					
						<td>${fieldValue(bean: proMatchUpInstance, field: "homeScore")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${proMatchUpInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
