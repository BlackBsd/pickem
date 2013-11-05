package com.schlomp.pickem

import com.schlomp.pickem.security.User
import grails.plugin.springsecurity.annotation.Secured

@Secured(["hasRole('ROLE_USER')"])
class HomeController {

    def springSecurityService

    @Secured("permitAll")
    def index() {
        def currentUser = (User) springSecurityService.getCurrentUser()

        [displayName: currentUser?.getDisplayName() ?: "Anonymous"]
    }
}
