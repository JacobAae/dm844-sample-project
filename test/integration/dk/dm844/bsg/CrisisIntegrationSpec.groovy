package dk.dm844.bsg

import grails.test.spock.IntegrationSpec

class CrisisIntegrationSpec extends IntegrationSpec {

	// tag::crisis-handling-setup[]
    def setup() {
	    // A bit ugly, but lets clear db first
	    Person.list()*.id.each {
		    Person.get(it).delete()
	    }
	    Ship.list()*.id.each {
		    Ship.get(it).delete(flush: true)
	    }

	    Ship battlestarGalactica = new Ship(name: 'Galactica', crewsize: 1337, shiptype: Shiptype.MILITARY, productionDate: new Date()).save(failOnError: true)
	    battlestarGalactica.addToCrewmembers(name: 'Kara Trace')
	    battlestarGalactica.addToCrewmembers(name: 'Lee Adama')
	    battlestarGalactica.addToCrewmembers(name: 'Sharon Agathon')

	    Ship astralQueen = new Ship(name: 'Astral Queen', crewsize: 1337, shiptype: Shiptype.MILITARY, productionDate: new Date()).save(failOnError: true)
	    astralQueen.addToCrewmembers(name: 'Tom Zarek')
    }
	// end::crisis-handling-setup[]

    def cleanup() {
    }

	// tag::crisis-handling[]
    void "Test crisis handling"() {
		when:
		Crisis crisis = new Crisis(header: 'Prison riot', solved: false)

	    then:
	    crisis.validate()
	    crisis.save()

		when:
		List<Ship> ships = Ship.list()
		crisis.addToAffectedShips(ships[0])
		crisis.addToAffectedShips(ships[1])

	    then:
	    crisis.affectedShips.size() == 2

	    when:
	    Ship battlestarGalactica = Ship.findByName('Galactica')

	    then:
	    battlestarGalactica.affectedBy
	    battlestarGalactica.affectedBy.size() == 1

	    when:
	    Person tomZarek = Person.findByName('Tom Zarek')
	    crisis.addToSolvers(tomZarek)
	    crisis.addToSolvers(Person.findByName('Lee Adama'))

	    then:
	    Person.findByName('Lee Adama').solvedCrisis.size() == 1

	    when: 'Neew to remove person from associations before we can delete him'
	    tomZarek.homeShip.removeFromCrewmembers(tomZarek)
	    tomZarek.delete(flush: true)

	    then:
	    Crisis.count() == 1
	    Person.count() == 3
	    Ship.findByName('Astral Queen').crewmembers.size() == 0
    }
	// tag::crisis-handling[]

}
