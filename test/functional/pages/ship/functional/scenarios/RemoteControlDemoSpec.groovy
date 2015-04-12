package pages.ship.functional.scenarios

import dk.dm844.bsg.Ship
import dk.dm844.bsg.Shiptype
import geb.spock.GebReportingSpec
import grails.plugin.remotecontrol.RemoteControl
import spock.lang.Ignore
import spock.lang.Stepwise

@Stepwise
class RemoteControlDemoSpec extends GebReportingSpec {

    def remote = new RemoteControl()

    @Ignore
    void "This does not work, as you are not running in the same JVM when performing Geb tests"() {
        setup: 'Create the ship - imagine this was not a publicly available action'
        Ship ship = new Ship(name: "Demetrious", shiptype: Shiptype.MISC, crewsize: 17, description: 'Demetrius is a sewage processing ship')
        ship.save()
        def id = ship.id

        when: 'Go to list page'
        to pages.ship.functional.pages.ship.ListPage

        then: 'Check Demetrious is actually shown'
        findModuleForName('Demetrious')
        findModuleForName('Demetrious').shiptype == 'MISC'

        cleanup: 'You can delete it again if you do not need it'
        Ship.get(id).delete()
    }

    void "Demo test interaction with the application through remote control"() {
        setup: 'Create the ship - imagine this was not a publicly available action'
        def id = remote {
            Ship ship = new Ship(name: "Demetrious", shiptype: Shiptype.MISC, crewsize: 17, description: 'Demetrius is a sewage processing ship')
            ship.save()
            id = ship.id
        }

        when: 'Go to list page'
        to pages.ship.functional.pages.ship.ListPage

        then: 'Check Demetrious is actually shown'
        findModuleForName('Demetrious')
        findModuleForName('Demetrious').shiptype == 'MISC'

        cleanup: 'You can delete it again if you do not need it'
        remote {
            Ship.get(id).delete()
        }
    }
}
