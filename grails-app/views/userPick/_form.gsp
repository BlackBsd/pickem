<%@ page import="com.schlomp.pickem.UserPick" %>



<div class="fieldcontain ${hasErrors(bean: userPickInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="userPick.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${com.schlomp.pickem.security.User.list()}" optionKey="id" required="" value="${userPickInstance?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userPickInstance, field: 'matchUp', 'error')} required">
	<label for="matchUp">
		<g:message code="userPick.matchUp.label" default="Match Up" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="matchUp" name="matchUp.id" from="${com.schlomp.pickem.ProMatchUp.list()}" optionKey="id" required="" value="${userPickInstance?.matchUp?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userPickInstance, field: 'winner', 'error')} required">
	<label for="winner">
		<g:message code="userPick.winner.label" default="Winner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="winner" name="winner.id" from="${com.schlomp.pickem.ProTeam.list()}" optionKey="id" required="" value="${userPickInstance?.winner?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userPickInstance, field: 'homeScore', 'error')} ">
	<label for="homeScore">
		<g:message code="userPick.homeScore.label" default="Home Score" />
		
	</label>
	<g:field name="homeScore" type="number" value="${userPickInstance.homeScore}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userPickInstance, field: 'awayScore', 'error')} ">
	<label for="awayScore">
		<g:message code="userPick.awayScore.label" default="Away Score" />
		
	</label>
	<g:field name="awayScore" type="number" value="${userPickInstance.awayScore}"/>
</div>

