<%@ page import="com.schlomp.pickem.ProWeek" %>



<div class="fieldcontain ${hasErrors(bean: proWeekInstance, field: 'weekNumber', 'error')} required">
	<label for="weekNumber">
		<g:message code="proWeek.weekNumber.label" default="Week Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="weekNumber" type="number" value="${proWeekInstance.weekNumber}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: proWeekInstance, field: 'proMatchUps', 'error')} ">
	<label for="proMatchUps">
		<g:message code="proWeek.proMatchUps.label" default="Pro Match Ups" />
		
	</label>
	<g:select name="proMatchUps" from="${com.schlomp.pickem.ProMatchUp.list()}" multiple="multiple" optionKey="id" size="5" value="${proWeekInstance?.proMatchUps*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proWeekInstance, field: 'byeTeams', 'error')} ">
	<label for="byeTeams">
		<g:message code="proWeek.byeTeams.label" default="Bye Teams" />
		
	</label>
	<g:select name="byeTeams" from="${com.schlomp.pickem.ProTeam.list()}" multiple="multiple" optionKey="id" size="5" value="${proWeekInstance?.byeTeams*.id}" class="many-to-many"/>
</div>

