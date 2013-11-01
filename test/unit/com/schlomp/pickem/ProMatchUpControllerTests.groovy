package com.schlomp.pickem



import org.junit.*
import grails.test.mixin.*

@TestFor(ProMatchUpController)
@Mock(ProMatchUp)
class ProMatchUpControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/proMatchUp/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.proMatchUpInstanceList.size() == 0
        assert model.proMatchUpInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.proMatchUpInstance != null
    }

    void testSave() {
        controller.save()

        assert model.proMatchUpInstance != null
        assert view == '/proMatchUp/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/proMatchUp/show/1'
        assert controller.flash.message != null
        assert ProMatchUp.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/proMatchUp/list'

        populateValidParams(params)
        def proMatchUp = new ProMatchUp(params)

        assert proMatchUp.save() != null

        params.id = proMatchUp.id

        def model = controller.show()

        assert model.proMatchUpInstance == proMatchUp
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/proMatchUp/list'

        populateValidParams(params)
        def proMatchUp = new ProMatchUp(params)

        assert proMatchUp.save() != null

        params.id = proMatchUp.id

        def model = controller.edit()

        assert model.proMatchUpInstance == proMatchUp
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/proMatchUp/list'

        response.reset()

        populateValidParams(params)
        def proMatchUp = new ProMatchUp(params)

        assert proMatchUp.save() != null

        // test invalid parameters in update
        params.id = proMatchUp.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/proMatchUp/edit"
        assert model.proMatchUpInstance != null

        proMatchUp.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/proMatchUp/show/$proMatchUp.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        proMatchUp.clearErrors()

        populateValidParams(params)
        params.id = proMatchUp.id
        params.version = -1
        controller.update()

        assert view == "/proMatchUp/edit"
        assert model.proMatchUpInstance != null
        assert model.proMatchUpInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/proMatchUp/list'

        response.reset()

        populateValidParams(params)
        def proMatchUp = new ProMatchUp(params)

        assert proMatchUp.save() != null
        assert ProMatchUp.count() == 1

        params.id = proMatchUp.id

        controller.delete()

        assert ProMatchUp.count() == 0
        assert ProMatchUp.get(proMatchUp.id) == null
        assert response.redirectedUrl == '/proMatchUp/list'
    }
}
