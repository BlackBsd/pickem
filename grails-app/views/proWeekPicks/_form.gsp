<%@ page import="com.schlomp.pickem.ProWeekPicks" %>



<div class="fieldcontain ${hasErrors(bean: proWeekPicksInstance, field: 'proWeek', 'error')} required">
	<label for="proWeek">
		<g:message code="proWeekPicks.proWeek.label" default="Pro Week" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="proWeek" name="proWeek.id" from="${com.schlomp.pickem.ProWeek.list()}" optionKey="id" required="" value="${proWeekPicksInstance?.proWeek?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proWeekPicksInstance, field: 'userPicks', 'error')} ">
	<label for="userPicks">
		<g:message code="proWeekPicks.userPicks.label" default="User Picks" />
		
	</label>
	<g:select name="userPicks" from="${com.schlomp.pickem.UserPick.list()}" multiple="multiple" optionKey="id" size="5" value="${proWeekPicksInstance?.userPicks*.id}" class="many-to-many"/>
</div>

