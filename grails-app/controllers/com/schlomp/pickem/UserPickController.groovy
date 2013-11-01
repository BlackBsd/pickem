package com.schlomp.pickem

import org.springframework.dao.DataIntegrityViolationException

class UserPickController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userPickInstanceList: UserPick.list(params), userPickInstanceTotal: UserPick.count()]
    }

    def create() {
        [userPickInstance: new UserPick(params)]
    }

    def save() {
        def userPickInstance = new UserPick(params)
        if (!userPickInstance.save(flush: true)) {
            render(view: "create", model: [userPickInstance: userPickInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'userPick.label', default: 'UserPick'), userPickInstance.id])
        redirect(action: "show", id: userPickInstance.id)
    }

    def show(Long id) {
        def userPickInstance = UserPick.get(id)
        if (!userPickInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userPick.label', default: 'UserPick'), id])
            redirect(action: "list")
            return
        }

        [userPickInstance: userPickInstance]
    }

    def edit(Long id) {
        def userPickInstance = UserPick.get(id)
        if (!userPickInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userPick.label', default: 'UserPick'), id])
            redirect(action: "list")
            return
        }

        [userPickInstance: userPickInstance]
    }

    def update(Long id, Long version) {
        def userPickInstance = UserPick.get(id)
        if (!userPickInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userPick.label', default: 'UserPick'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userPickInstance.version > version) {
                userPickInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'userPick.label', default: 'UserPick')] as Object[],
                          "Another user has updated this UserPick while you were editing")
                render(view: "edit", model: [userPickInstance: userPickInstance])
                return
            }
        }

        userPickInstance.properties = params

        if (!userPickInstance.save(flush: true)) {
            render(view: "edit", model: [userPickInstance: userPickInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'userPick.label', default: 'UserPick'), userPickInstance.id])
        redirect(action: "show", id: userPickInstance.id)
    }

    def delete(Long id) {
        def userPickInstance = UserPick.get(id)
        if (!userPickInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userPick.label', default: 'UserPick'), id])
            redirect(action: "list")
            return
        }

        try {
            userPickInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'userPick.label', default: 'UserPick'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'userPick.label', default: 'UserPick'), id])
            redirect(action: "show", id: id)
        }
    }
}
