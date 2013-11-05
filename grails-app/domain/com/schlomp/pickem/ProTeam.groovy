package com.schlomp.pickem

class ProTeam {

    static constraints = {
        name(nullable: false)
        location(nullable: false)
        conference(nullable: false)
        division(nullable: false)
    }

    static enum Conference {
        NFC("NFC"), AFC("AFC")
        String name
        Conference(String name) {this.name = name}
        public String toString() {return this.name}
    }

    static enum Division {
        NORTH("North"), EAST("East"), SOUTH("South"), WEST("West")
        String name
        Division(String name) {this.name = name}
        public String toString() {return this.name}
    }

    String name
    String location
    Conference conference
    Division division

    String toString() {
        return location + " " + name
    }
}
