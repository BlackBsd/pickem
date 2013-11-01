package com.schlomp.pickem



import org.junit.*
import grails.test.mixin.*

@TestFor(ProWeekController)
@Mock(ProWeek)
class ProWeekControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/proWeek/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.proWeekInstanceList.size() == 0
        assert model.proWeekInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.proWeekInstance != null
    }

    void testSave() {
        controller.save()

        assert model.proWeekInstance != null
        assert view == '/proWeek/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/proWeek/show/1'
        assert controller.flash.message != null
        assert ProWeek.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/proWeek/list'

        populateValidParams(params)
        def proWeek = new ProWeek(params)

        assert proWeek.save() != null

        params.id = proWeek.id

        def model = controller.show()

        assert model.proWeekInstance == proWeek
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/proWeek/list'

        populateValidParams(params)
        def proWeek = new ProWeek(params)

        assert proWeek.save() != null

        params.id = proWeek.id

        def model = controller.edit()

        assert model.proWeekInstance == proWeek
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/proWeek/list'

        response.reset()

        populateValidParams(params)
        def proWeek = new ProWeek(params)

        assert proWeek.save() != null

        // test invalid parameters in update
        params.id = proWeek.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/proWeek/edit"
        assert model.proWeekInstance != null

        proWeek.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/proWeek/show/$proWeek.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        proWeek.clearErrors()

        populateValidParams(params)
        params.id = proWeek.id
        params.version = -1
        controller.update()

        assert view == "/proWeek/edit"
        assert model.proWeekInstance != null
        assert model.proWeekInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/proWeek/list'

        response.reset()

        populateValidParams(params)
        def proWeek = new ProWeek(params)

        assert proWeek.save() != null
        assert ProWeek.count() == 1

        params.id = proWeek.id

        controller.delete()

        assert ProWeek.count() == 0
        assert ProWeek.get(proWeek.id) == null
        assert response.redirectedUrl == '/proWeek/list'
    }
}
