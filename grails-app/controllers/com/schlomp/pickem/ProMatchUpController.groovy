package com.schlomp.pickem

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured("ROLE_ADMIN")
class ProMatchUpController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [proMatchUpInstanceList: ProMatchUp.list(params), proMatchUpInstanceTotal: ProMatchUp.count()]
    }

    def create() {
        [proMatchUpInstance: new ProMatchUp(params)]
    }

    def save() {
        def proMatchUpInstance = new ProMatchUp(params)
        if (!proMatchUpInstance.save(flush: true)) {
            render(view: "create", model: [proMatchUpInstance: proMatchUpInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'proMatchUp.label', default: 'ProMatchUp'), proMatchUpInstance.id])
        redirect(action: "show", id: proMatchUpInstance.id)
    }

    def show(Long id) {
        def proMatchUpInstance = ProMatchUp.get(id)
        if (!proMatchUpInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proMatchUp.label', default: 'ProMatchUp'), id])
            redirect(action: "list")
            return
        }

        [proMatchUpInstance: proMatchUpInstance]
    }

    def edit(Long id) {
        def proMatchUpInstance = ProMatchUp.get(id)
        if (!proMatchUpInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proMatchUp.label', default: 'ProMatchUp'), id])
            redirect(action: "list")
            return
        }

        [proMatchUpInstance: proMatchUpInstance]
    }

    def update(Long id, Long version) {
        def proMatchUpInstance = ProMatchUp.get(id)
        if (!proMatchUpInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proMatchUp.label', default: 'ProMatchUp'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (proMatchUpInstance.version > version) {
                proMatchUpInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'proMatchUp.label', default: 'ProMatchUp')] as Object[],
                          "Another user has updated this ProMatchUp while you were editing")
                render(view: "edit", model: [proMatchUpInstance: proMatchUpInstance])
                return
            }
        }

        proMatchUpInstance.properties = params

        if (!proMatchUpInstance.save(flush: true)) {
            render(view: "edit", model: [proMatchUpInstance: proMatchUpInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'proMatchUp.label', default: 'ProMatchUp'), proMatchUpInstance.id])
        redirect(action: "show", id: proMatchUpInstance.id)
    }

    def delete(Long id) {
        def proMatchUpInstance = ProMatchUp.get(id)
        if (!proMatchUpInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proMatchUp.label', default: 'ProMatchUp'), id])
            redirect(action: "list")
            return
        }

        try {
            proMatchUpInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'proMatchUp.label', default: 'ProMatchUp'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'proMatchUp.label', default: 'ProMatchUp'), id])
            redirect(action: "show", id: id)
        }
    }
}
