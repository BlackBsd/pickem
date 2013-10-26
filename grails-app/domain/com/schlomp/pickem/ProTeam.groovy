package com.schlomp.pickem

class ProTeam {

    static constraints = {
        name(nullable: false)
        location(nullable: false)
    }

    String name
    String location
}
