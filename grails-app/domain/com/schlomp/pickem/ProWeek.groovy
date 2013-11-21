package com.schlomp.pickem

class ProWeek {

    static constraints = {
        weekNumber(nullable: false)
    }

    static hasMany = [
            proMatchUps: ProMatchUp,
            byeTeams: ProTeam
    ]

    int weekNumber

    String toString() {
        return "Week #" + weekNumber
    }
}
