package com.schlomp.pickem

class ProWeek {

    static constraints = {
        weekNumber(nullable: false)
        proMatchUps(unique: true)
        byeTeams(unique: true)
    }

    static hasMany = [
            proMatchUps: ProMatchUp,
            byeTeams: ProTeam
    ]

    int weekNumber
}
