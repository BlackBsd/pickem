package com.schlomp.pickem

class ProWeekPicks {

    static constraints = {
        proWeek(nullable: false)
        userPicks(unique: true)
    }

    static hasMany = [
            userPicks : UserPick
    ]

    static belongsTo = [
            proWeek: ProWeek
    ]
}
