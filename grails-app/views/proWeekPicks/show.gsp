
<%@ page import="com.schlomp.pickem.ProWeekPicks" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'proWeekPicks.label', default: 'ProWeekPicks')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-proWeekPicks" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-proWeekPicks" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list proWeekPicks">
			
				<g:if test="${proWeekPicksInstance?.proWeek}">
				<li class="fieldcontain">
					<span id="proWeek-label" class="property-label"><g:message code="proWeekPicks.proWeek.label" default="Pro Week" /></span>
					
						<span class="property-value" aria-labelledby="proWeek-label"><g:link controller="proWeek" action="show" id="${proWeekPicksInstance?.proWeek?.id}">${proWeekPicksInstance?.proWeek?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${proWeekPicksInstance?.userPicks}">
				<li class="fieldcontain">
					<span id="userPicks-label" class="property-label"><g:message code="proWeekPicks.userPicks.label" default="User Picks" /></span>
					
						<g:each in="${proWeekPicksInstance.userPicks}" var="u">
						<span class="property-value" aria-labelledby="userPicks-label"><g:link controller="userPick" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${proWeekPicksInstance?.id}" />
					<g:link class="edit" action="edit" id="${proWeekPicksInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
