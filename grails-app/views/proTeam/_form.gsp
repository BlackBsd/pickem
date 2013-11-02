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

<div class="fieldcontain ${hasErrors(bean: proTeamInstance, field: 'conference', 'error')} required">
	<label for="conference">
		<g:message code="proTeam.conference.label" default="Conference" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="conference" from="${com.schlomp.pickem.ProTeam$Conference?.values()}" keys="${com.schlomp.pickem.ProTeam$Conference.values()*.name()}" required="" value="${proTeamInstance?.conference?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proTeamInstance, field: 'division', 'error')} required">
	<label for="division">
		<g:message code="proTeam.division.label" default="Division" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="division" from="${com.schlomp.pickem.ProTeam$Division?.values()}" keys="${com.schlomp.pickem.ProTeam$Division.values()*.name()}" required="" value="${proTeamInstance?.division?.name()}"/>
</div>

