package com.schlomp.pickem

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(["hasRole('ROLE_ADMIN')"])
class ProTeamController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [proTeamInstanceList: ProTeam.list(params), proTeamInstanceTotal: ProTeam.count()]
    }

    def create() {
        [proTeamInstance: new ProTeam(params)]
    }

    def save() {
        def proTeamInstance = new ProTeam(params)
        if (!proTeamInstance.save(flush: true)) {
            render(view: "create", model: [proTeamInstance: proTeamInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'proTeam.label', default: 'ProTeam'), proTeamInstance.id])
        redirect(action: "show", id: proTeamInstance.id)
    }

    def show(Long id) {
        def proTeamInstance = ProTeam.get(id)
        if (!proTeamInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proTeam.label', default: 'ProTeam'), id])
            redirect(action: "list")
            return
        }

        [proTeamInstance: proTeamInstance]
    }

    def edit(Long id) {
        def proTeamInstance = ProTeam.get(id)
        if (!proTeamInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proTeam.label', default: 'ProTeam'), id])
            redirect(action: "list")
            return
        }

        [proTeamInstance: proTeamInstance]
    }

    def update(Long id, Long version) {
        def proTeamInstance = ProTeam.get(id)
        if (!proTeamInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proTeam.label', default: 'ProTeam'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (proTeamInstance.version > version) {
                proTeamInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'proTeam.label', default: 'ProTeam')] as Object[],
                          "Another user has updated this ProTeam while you were editing")
                render(view: "edit", model: [proTeamInstance: proTeamInstance])
                return
            }
        }

        proTeamInstance.properties = params

        if (!proTeamInstance.save(flush: true)) {
            render(view: "edit", model: [proTeamInstance: proTeamInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'proTeam.label', default: 'ProTeam'), proTeamInstance.id])
        redirect(action: "show", id: proTeamInstance.id)
    }

    def delete(Long id) {
        def proTeamInstance = ProTeam.get(id)
        if (!proTeamInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proTeam.label', default: 'ProTeam'), id])
            redirect(action: "list")
            return
        }

        try {
            proTeamInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'proTeam.label', default: 'ProTeam'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'proTeam.label', default: 'ProTeam'), id])
            redirect(action: "show", id: id)
        }
    }
}
