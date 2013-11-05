package com.schlomp.pickem

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(["hasRole('ROLE_ADMIN')"])
class ProWeekController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [proWeekInstanceList: ProWeek.list(params), proWeekInstanceTotal: ProWeek.count()]
    }

    def create() {
        [proWeekInstance: new ProWeek(params)]
    }

    def save() {
        def proWeekInstance = new ProWeek(params)
        if (!proWeekInstance.save(flush: true)) {
            render(view: "create", model: [proWeekInstance: proWeekInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'proWeek.label', default: 'ProWeek'), proWeekInstance.id])
        redirect(action: "show", id: proWeekInstance.id)
    }

    def show(Long id) {
        def proWeekInstance = ProWeek.get(id)
        if (!proWeekInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proWeek.label', default: 'ProWeek'), id])
            redirect(action: "list")
            return
        }

        [proWeekInstance: proWeekInstance]
    }

    def edit(Long id) {
        def proWeekInstance = ProWeek.get(id)
        if (!proWeekInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proWeek.label', default: 'ProWeek'), id])
            redirect(action: "list")
            return
        }

        [proWeekInstance: proWeekInstance]
    }

    def update(Long id, Long version) {
        def proWeekInstance = ProWeek.get(id)
        if (!proWeekInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proWeek.label', default: 'ProWeek'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (proWeekInstance.version > version) {
                proWeekInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'proWeek.label', default: 'ProWeek')] as Object[],
                          "Another user has updated this ProWeek while you were editing")
                render(view: "edit", model: [proWeekInstance: proWeekInstance])
                return
            }
        }

        proWeekInstance.properties = params

        if (!proWeekInstance.save(flush: true)) {
            render(view: "edit", model: [proWeekInstance: proWeekInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'proWeek.label', default: 'ProWeek'), proWeekInstance.id])
        redirect(action: "show", id: proWeekInstance.id)
    }

    def delete(Long id) {
        def proWeekInstance = ProWeek.get(id)
        if (!proWeekInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proWeek.label', default: 'ProWeek'), id])
            redirect(action: "list")
            return
        }

        try {
            proWeekInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'proWeek.label', default: 'ProWeek'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'proWeek.label', default: 'ProWeek'), id])
            redirect(action: "show", id: id)
        }
    }
}
