package com.schlomp.pickem

import com.schlomp.pickem.security.User

class UserPick {

    static constraints = {
        user(nullable: false)
        matchUp(nullable: false)
        winner(nullable: false)

        homeScore(nullable: true)
        awayScore(nullable: true)
    }

    User user
    ProMatchUp matchUp
    ProTeam winner

    Integer homeScore = null
    Integer awayScore = null
}
