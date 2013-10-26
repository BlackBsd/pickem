package com.schlomp.pickem



import org.junit.*
import grails.test.mixin.*

@TestFor(ProTeamController)
@Mock(ProTeam)
class ProTeamControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/proTeam/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.proTeamInstanceList.size() == 0
        assert model.proTeamInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.proTeamInstance != null
    }

    void testSave() {
        controller.save()

        assert model.proTeamInstance != null
        assert view == '/proTeam/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/proTeam/show/1'
        assert controller.flash.message != null
        assert ProTeam.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/proTeam/list'

        populateValidParams(params)
        def proTeam = new ProTeam(params)

        assert proTeam.save() != null

        params.id = proTeam.id

        def model = controller.show()

        assert model.proTeamInstance == proTeam
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/proTeam/list'

        populateValidParams(params)
        def proTeam = new ProTeam(params)

        assert proTeam.save() != null

        params.id = proTeam.id

        def model = controller.edit()

        assert model.proTeamInstance == proTeam
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/proTeam/list'

        response.reset()

        populateValidParams(params)
        def proTeam = new ProTeam(params)

        assert proTeam.save() != null

        // test invalid parameters in update
        params.id = proTeam.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/proTeam/edit"
        assert model.proTeamInstance != null

        proTeam.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/proTeam/show/$proTeam.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        proTeam.clearErrors()

        populateValidParams(params)
        params.id = proTeam.id
        params.version = -1
        controller.update()

        assert view == "/proTeam/edit"
        assert model.proTeamInstance != null
        assert model.proTeamInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/proTeam/list'

        response.reset()

        populateValidParams(params)
        def proTeam = new ProTeam(params)

        assert proTeam.save() != null
        assert ProTeam.count() == 1

        params.id = proTeam.id

        controller.delete()

        assert ProTeam.count() == 0
        assert ProTeam.get(proTeam.id) == null
        assert response.redirectedUrl == '/proTeam/list'
    }
}
