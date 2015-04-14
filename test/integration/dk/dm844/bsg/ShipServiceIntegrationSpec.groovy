package dk.dm844.bsg

import grails.test.spock.IntegrationSpec


class ShipServiceIntegrationSpec extends IntegrationSpec {

    ShipService shipService

    void setup() {
        toemDatabasenForShips()
    }

    void "Test at jeg får en tom liste når der ikke er noget i databasen"() {
        expect:
        shipService.findAllShips(10) == []
    }

    void "Test at jeg får en liste med 1 element når db indeholder 1 element"() {
        setup:
        opretShips(1)

        when:
        List ships = shipService.findAllShips()

        then:
        ships
        ships.size() == 1
        ships.first().name == 'Colonial One'
    }

    void "Test at jeg får en liste med 3 element når db indeholder 5 element men vi har max 3 på"() {
        setup:
        opretShips()

        when:
        List ships = shipService.findAllShips(3)

        then:
        ships
        ships.size() == 3
    }

    void "Test alfabetisk rækkefølge"() {
        setup:
        opretShips()

        when:
        List ships = shipService.findAllShips(3, 'desc')

        then:
        ships
        ships*.name == [ 'Colonial One', 'Cloud 9', 'Battlestar Pegasus', ]

    }

    void opretShips(int antal = 5) {
        ['Colonial One','Battlestar Galactica','Battlestar Pegasus','Astral Queen', 'Cloud 9'].subList(0,antal).each {
            Ship ship = new Ship(name: it, crewsize: 10, shiptype: Shiptype.ACCOMODATION, productionDate: new Date())
            ship.save(failOnError: true)
        }
    }

    void toemDatabasenForShips() {
        Ship.all*.id.each {
            Ship.get(it).delete(flush: true)
        }
    }
}
