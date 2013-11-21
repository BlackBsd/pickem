package com.schlomp.pickem

class ProWeekPicks {

    static constraints = {
        proWeek(nullable: false)
    }

    static hasMany = [
            userPicks : UserPick
    ]

    static belongsTo = [
            proWeek: ProWeek
    ]
}
