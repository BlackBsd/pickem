import com.schlomp.pickem.ProMatchUp
import com.schlomp.pickem.ProTeam
import com.schlomp.pickem.ProWeek
import com.schlomp.pickem.ProWeekPicks
import com.schlomp.pickem.UserPick
import com.schlomp.pickem.security.Role
import com.schlomp.pickem.security.User
import com.schlomp.pickem.security.UserRole
import grails.util.Environment
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

class BootStrap {

    def init = { servletContext ->
        switch (Environment.current) {
            case Environment.DEVELOPMENT:
                setupTeams()
                setupRoles()
                setupUsers()
                setupUserRoles()

                setupDevSeason()
                break

            case Environment.TEST:
                //Something set up things for tests!
                break

            case Environment.PRODUCTION:
                //I don't think we should bootstrap anything here
                //Except maybe an admin user, and the user roles
                break
        }
    }

    def destroy = {
    }

    static private def setupRoles() {
        new Role(authority: "ROLE_USER").save(failOnError: true)
        new Role(authority: "ROLE_ADMIN").save(failOnError: true)
    }

    static private def setupUsers() {
        def users = ["tom", "dick", "harry", "jane"]

        users.each() {
            new User(username: it, password: it, displayName: "DN_" + it).save(failOnError: true)
        }

        new User(username : "admin", password : "admin", displayName: "DN_admin").save(failOnError: true)
    }

    static private def setupUserRoles() {
        def userRole = Role.findByAuthority("ROLE_USER");
        def adminRole = Role.findByAuthority("ROLE_ADMIN");

        new UserRole(user: User.findByUsername("tom"), role: userRole).save(failOnError: true)
        new UserRole(user: User.findByUsername("dick"), role: userRole).save(failOnError: true)
        new UserRole(user: User.findByUsername("harry"), role: userRole).save(failOnError: true)
        new UserRole(user: User.findByUsername("jane"), role: userRole).save(failOnError: true)
        new UserRole(user: User.findByUsername("admin"), role: adminRole).save(failOnError: true)
    }

    static private def setupTeams() {
        def afcEastTeams = [
                "Buffalo": "Bills",
                "Miami": "Dolphins",
                "New England": "Patriots",
                "New York" : "Jets"
        ]

        def afcNorthTeams = [
                "Baltimore" : "Ravens",
                "Cincinnati" : "Bengals",
                "Cleveland" : "Browns",
                "Pittsburgh" : "Steelers"
        ]

        def afcSouth = [
                "Houston" : "Texans",
                "Indianapolis" : "Colts",
                "Jacksonville" : "Jaguars",
                "Tennessee" : "Titans"
        ]

        def afcWest = [
                "Denver" : "Broncos",
                "Kansas City" : "Chiefs",
                "Oakland" : "Raiders",
                "San Diego" : "Chargers"
        ]

        def nfcEastTeams = [
                "Dallas" : "Cowboys",
                "New York" : "Giants",
                "Philadelphia" : "Eagles",
                "Washington" : "Redskins"
        ]

        def nfcNorthTeams = [
                "Detroit" : "Lions",
                "Green Bay" : "Packers",
                "Minnesota" : "Vikings",
                "Chicago" : "Bears"
        ]

        def nfcSouthTeams = [
                "Atlanta" : "Falcons",
                "Carolina" : "Panthers",
                "New Orleans" : "Saints",
                "Tampa Bay" : "Buccaneers"
        ]

        def nfcBestTeams = [
                "Arizona" : "Cardinals",
                "St. Louis" : "Rams",
                "San Francisco" : "49ers",
                "Seattle" : "Seahawks"
        ]

        def teamDirectory = [
                (ProTeam.Conference.AFC) : [
                        (ProTeam.Division.EAST) : afcEastTeams,
                        (ProTeam.Division.NORTH) : afcNorthTeams,
                        (ProTeam.Division.SOUTH) : afcSouth,
                        (ProTeam.Division.WEST) : afcWest
                ],
                (ProTeam.Conference.NFC) : [
                        (ProTeam.Division.EAST) : nfcEastTeams,
                        (ProTeam.Division.NORTH) : nfcNorthTeams,
                        (ProTeam.Division.SOUTH) : nfcSouthTeams,
                        (ProTeam.Division.WEST) : nfcBestTeams
                ]
        ]

        teamDirectory.each() { conf, divisions ->
            divisions.each() { division, teams ->
                teams.each() { city, name ->
                    new ProTeam(name : name, location : city, conference : conf, division : division).save(failOnError : true)
                }
            }
        }
    }

    static private def setupDevSeason() {
        def aTeam = ProTeam.findByName("49ers")
        def bTeam = ProTeam.findByName("Bears")
        def cTeam = ProTeam.findByName("Redskins")

        def time = new DateTime(2013, 12, 25, 21, 0, DateTimeZone.UTC);
        def match = new ProMatchUp(homeTeam: aTeam, awayTeam: bTeam, isTieBreaker: true, gameTime: time)
        match.save(failOnError: true)

        def week = new ProWeek()
        week.setWeekNumber(1)
        week.addToByeTeams(cTeam)
        week.addToProMatchUps(match)
        week.save(failOnError: true)

        def admin = User.findByUsername("admin")
        def adminPick = new UserPick(matchUp: match, user: admin, winner: aTeam)
        adminPick.save(failOnError: true)

        def weekPicks = new ProWeekPicks(userPicks: [adminPick], proWeek: week)
        weekPicks.save(failOnError: true)
    }
}
