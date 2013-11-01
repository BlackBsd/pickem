package com.schlomp.pickem

import org.springframework.dao.DataIntegrityViolationException

class ProWeekPicksController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [proWeekPicksInstanceList: ProWeekPicks.list(params), proWeekPicksInstanceTotal: ProWeekPicks.count()]
    }

    def create() {
        [proWeekPicksInstance: new ProWeekPicks(params)]
    }

    def save() {
        def proWeekPicksInstance = new ProWeekPicks(params)
        if (!proWeekPicksInstance.save(flush: true)) {
            render(view: "create", model: [proWeekPicksInstance: proWeekPicksInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'proWeekPicks.label', default: 'ProWeekPicks'), proWeekPicksInstance.id])
        redirect(action: "show", id: proWeekPicksInstance.id)
    }

    def show(Long id) {
        def proWeekPicksInstance = ProWeekPicks.get(id)
        if (!proWeekPicksInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proWeekPicks.label', default: 'ProWeekPicks'), id])
            redirect(action: "list")
            return
        }

        [proWeekPicksInstance: proWeekPicksInstance]
    }

    def edit(Long id) {
        def proWeekPicksInstance = ProWeekPicks.get(id)
        if (!proWeekPicksInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proWeekPicks.label', default: 'ProWeekPicks'), id])
            redirect(action: "list")
            return
        }

        [proWeekPicksInstance: proWeekPicksInstance]
    }

    def update(Long id, Long version) {
        def proWeekPicksInstance = ProWeekPicks.get(id)
        if (!proWeekPicksInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proWeekPicks.label', default: 'ProWeekPicks'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (proWeekPicksInstance.version > version) {
                proWeekPicksInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'proWeekPicks.label', default: 'ProWeekPicks')] as Object[],
                          "Another user has updated this ProWeekPicks while you were editing")
                render(view: "edit", model: [proWeekPicksInstance: proWeekPicksInstance])
                return
            }
        }

        proWeekPicksInstance.properties = params

        if (!proWeekPicksInstance.save(flush: true)) {
            render(view: "edit", model: [proWeekPicksInstance: proWeekPicksInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'proWeekPicks.label', default: 'ProWeekPicks'), proWeekPicksInstance.id])
        redirect(action: "show", id: proWeekPicksInstance.id)
    }

    def delete(Long id) {
        def proWeekPicksInstance = ProWeekPicks.get(id)
        if (!proWeekPicksInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proWeekPicks.label', default: 'ProWeekPicks'), id])
            redirect(action: "list")
            return
        }

        try {
            proWeekPicksInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'proWeekPicks.label', default: 'ProWeekPicks'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'proWeekPicks.label', default: 'ProWeekPicks'), id])
            redirect(action: "show", id: id)
        }
    }
}
