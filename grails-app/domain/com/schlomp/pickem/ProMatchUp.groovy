package com.schlomp.pickem

import org.joda.time.*
import org.joda.time.contrib.hibernate.PersistentDateTime

class ProMatchUp {

    static constraints = {
        homeTeam(nullable: false)
        awayTeam(nullable: false)
        isTieBreaker(nullable: false)
        gameTime(nullable: false)

        winningTeam(nullable: true)
        homeScore(nullable: true)
        awayScore(nullable: true)
    }

    static mapping = {
        gameTime type: PersistentDateTime
    }

    ProTeam homeTeam
    ProTeam awayTeam
    boolean isTieBreaker
    DateTime gameTime

    ProTeam winningTeam = null
    Integer homeScore = null
    Integer awayScore = null
}
