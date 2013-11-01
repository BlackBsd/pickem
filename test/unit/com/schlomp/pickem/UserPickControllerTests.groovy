package com.schlomp.pickem



import org.junit.*
import grails.test.mixin.*

@TestFor(UserPickController)
@Mock(UserPick)
class UserPickControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/userPick/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.userPickInstanceList.size() == 0
        assert model.userPickInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.userPickInstance != null
    }

    void testSave() {
        controller.save()

        assert model.userPickInstance != null
        assert view == '/userPick/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/userPick/show/1'
        assert controller.flash.message != null
        assert UserPick.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/userPick/list'

        populateValidParams(params)
        def userPick = new UserPick(params)

        assert userPick.save() != null

        params.id = userPick.id

        def model = controller.show()

        assert model.userPickInstance == userPick
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/userPick/list'

        populateValidParams(params)
        def userPick = new UserPick(params)

        assert userPick.save() != null

        params.id = userPick.id

        def model = controller.edit()

        assert model.userPickInstance == userPick
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/userPick/list'

        response.reset()

        populateValidParams(params)
        def userPick = new UserPick(params)

        assert userPick.save() != null

        // test invalid parameters in update
        params.id = userPick.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/userPick/edit"
        assert model.userPickInstance != null

        userPick.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/userPick/show/$userPick.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        userPick.clearErrors()

        populateValidParams(params)
        params.id = userPick.id
        params.version = -1
        controller.update()

        assert view == "/userPick/edit"
        assert model.userPickInstance != null
        assert model.userPickInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/userPick/list'

        response.reset()

        populateValidParams(params)
        def userPick = new UserPick(params)

        assert userPick.save() != null
        assert UserPick.count() == 1

        params.id = userPick.id

        controller.delete()

        assert UserPick.count() == 0
        assert UserPick.get(userPick.id) == null
        assert response.redirectedUrl == '/userPick/list'
    }
}
