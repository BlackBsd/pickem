
<%@ page import="com.schlomp.pickem.UserPick" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'userPick.label', default: 'UserPick')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-userPick" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-userPick" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="userPick.user.label" default="User" /></th>
					
						<th><g:message code="userPick.matchUp.label" default="Match Up" /></th>
					
						<th><g:message code="userPick.winner.label" default="Winner" /></th>
					
						<g:sortableColumn property="homeScore" title="${message(code: 'userPick.homeScore.label', default: 'Home Score')}" />
					
						<g:sortableColumn property="awayScore" title="${message(code: 'userPick.awayScore.label', default: 'Away Score')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${userPickInstanceList}" status="i" var="userPickInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${userPickInstance.id}">${fieldValue(bean: userPickInstance, field: "user")}</g:link></td>
					
						<td>${fieldValue(bean: userPickInstance, field: "matchUp")}</td>
					
						<td>${fieldValue(bean: userPickInstance, field: "winner")}</td>
					
						<td>${fieldValue(bean: userPickInstance, field: "homeScore")}</td>
					
						<td>${fieldValue(bean: userPickInstance, field: "awayScore")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userPickInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
