package com.schlomp.pickem



import org.junit.*
import grails.test.mixin.*

@TestFor(ProWeekPicksController)
@Mock(ProWeekPicks)
class ProWeekPicksControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/proWeekPicks/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.proWeekPicksInstanceList.size() == 0
        assert model.proWeekPicksInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.proWeekPicksInstance != null
    }

    void testSave() {
        controller.save()

        assert model.proWeekPicksInstance != null
        assert view == '/proWeekPicks/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/proWeekPicks/show/1'
        assert controller.flash.message != null
        assert ProWeekPicks.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/proWeekPicks/list'

        populateValidParams(params)
        def proWeekPicks = new ProWeekPicks(params)

        assert proWeekPicks.save() != null

        params.id = proWeekPicks.id

        def model = controller.show()

        assert model.proWeekPicksInstance == proWeekPicks
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/proWeekPicks/list'

        populateValidParams(params)
        def proWeekPicks = new ProWeekPicks(params)

        assert proWeekPicks.save() != null

        params.id = proWeekPicks.id

        def model = controller.edit()

        assert model.proWeekPicksInstance == proWeekPicks
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/proWeekPicks/list'

        response.reset()

        populateValidParams(params)
        def proWeekPicks = new ProWeekPicks(params)

        assert proWeekPicks.save() != null

        // test invalid parameters in update
        params.id = proWeekPicks.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/proWeekPicks/edit"
        assert model.proWeekPicksInstance != null

        proWeekPicks.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/proWeekPicks/show/$proWeekPicks.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        proWeekPicks.clearErrors()

        populateValidParams(params)
        params.id = proWeekPicks.id
        params.version = -1
        controller.update()

        assert view == "/proWeekPicks/edit"
        assert model.proWeekPicksInstance != null
        assert model.proWeekPicksInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/proWeekPicks/list'

        response.reset()

        populateValidParams(params)
        def proWeekPicks = new ProWeekPicks(params)

        assert proWeekPicks.save() != null
        assert ProWeekPicks.count() == 1

        params.id = proWeekPicks.id

        controller.delete()

        assert ProWeekPicks.count() == 0
        assert ProWeekPicks.get(proWeekPicks.id) == null
        assert response.redirectedUrl == '/proWeekPicks/list'
    }
}
