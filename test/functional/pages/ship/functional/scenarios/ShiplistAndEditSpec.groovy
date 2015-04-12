package pages.ship.functional.scenarios

import geb.spock.GebReportingSpec
import spock.lang.Stepwise

@Stepwise
class ShiplistAndEditSpec extends GebReportingSpec {

    void "Goto list of ships page"() {
        when:
        to pages.ship.functional.pages.ship.ListPage

        then:
        at pages.ship.functional.pages.ship.ListPage
    }

    void "Edit link brings me to edit page"() {
        when: 'Click on the edit link for Colonial One'
        def link = findModuleForName('Colonial One').editlink

        and:
        link.click()

        then:
        at pages.ship.functional.pages.ship.EditPage
    }

    void "Update category"() {
        when:
        form.shiptype = 'FREIGHT'
        submitButton.click()

        then:
        at pages.ship.functional.pages.ship.ShowPage
    }

    void "Check new value at ship list page"() {
        when:
        to pages.ship.functional.pages.ship.ListPage

        then:
        findModuleForName('Colonial One').shiptype == 'FREIGHT'
    }

}