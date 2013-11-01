<%@ page import="com.schlomp.pickem.ProMatchUp" %>



<div class="fieldcontain ${hasErrors(bean: proMatchUpInstance, field: 'homeTeam', 'error')} required">
	<label for="homeTeam">
		<g:message code="proMatchUp.homeTeam.label" default="Home Team" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="homeTeam" name="homeTeam.id" from="${com.schlomp.pickem.ProTeam.list()}" optionKey="id" required="" value="${proMatchUpInstance?.homeTeam?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proMatchUpInstance, field: 'awayTeam', 'error')} required">
	<label for="awayTeam">
		<g:message code="proMatchUp.awayTeam.label" default="Away Team" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="awayTeam" name="awayTeam.id" from="${com.schlomp.pickem.ProTeam.list()}" optionKey="id" required="" value="${proMatchUpInstance?.awayTeam?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proMatchUpInstance, field: 'isTieBreaker', 'error')} ">
	<label for="isTieBreaker">
		<g:message code="proMatchUp.isTieBreaker.label" default="Is Tie Breaker" />
		
	</label>
	<g:checkBox name="isTieBreaker" value="${proMatchUpInstance?.isTieBreaker}" />
</div>

<div class="fieldcontain ${hasErrors(bean: proMatchUpInstance, field: 'gameTime', 'error')} required">
	<label for="gameTime">
		<g:message code="proMatchUp.gameTime.label" default="Game Time" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: proMatchUpInstance, field: 'winningTeam', 'error')} ">
	<label for="winningTeam">
		<g:message code="proMatchUp.winningTeam.label" default="Winning Team" />
		
	</label>
	<g:select id="winningTeam" name="winningTeam.id" from="${com.schlomp.pickem.ProTeam.list()}" optionKey="id" value="${proMatchUpInstance?.winningTeam?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proMatchUpInstance, field: 'homeScore', 'error')} ">
	<label for="homeScore">
		<g:message code="proMatchUp.homeScore.label" default="Home Score" />
		
	</label>
	<g:field name="homeScore" type="number" value="${proMatchUpInstance.homeScore}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proMatchUpInstance, field: 'awayScore', 'error')} ">
	<label for="awayScore">
		<g:message code="proMatchUp.awayScore.label" default="Away Score" />
		
	</label>
	<g:field name="awayScore" type="number" value="${proMatchUpInstance.awayScore}"/>
</div>

