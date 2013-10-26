<%@ page import="com.schlomp.pickem.ProTeam" %>



<div class="fieldcontain ${hasErrors(bean: proTeamInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="proTeam.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${proTeamInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proTeamInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="proTeam.location.label" default="Location" />
		
	</label>
	<g:textField name="location" value="${proTeamInstance?.location}"/>
</div>

