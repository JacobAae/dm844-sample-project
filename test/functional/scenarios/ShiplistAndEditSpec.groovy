package scenarios

import geb.spock.GebReportingSpec
import pages.ship.EditPage
import pages.ship.ListPage
import pages.ship.ShowPage
import spock.lang.Stepwise


@Stepwise
class ShiplistAndEditSpec extends GebReportingSpec {

    void "Goto list of ships page"() {
        when:
        to ListPage

        then:
        at ListPage
    }

    void "Edit link brings me to edit page"() {
        when: 'Click on the edit link for Colonial One'
        def link = findModuleForName('Colonial One').editlink

        and:
        link.click()

        then:
        at EditPage
    }

    void "Update category"() {
        when:
        form.shiptype = 'FREIGHT'
        submitButton.click()

        then:
        at ShowPage
    }

    void "Check new value at ship list page"() {
        when:
        to ListPage

        then:
        findModuleForName('Colonial One').shiptype == 'FREIGHT'
    }

}