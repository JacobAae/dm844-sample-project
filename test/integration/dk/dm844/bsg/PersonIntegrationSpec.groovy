package dk.dm844.bsg

import grails.test.spock.IntegrationSpec

class PersonIntegrationSpec extends IntegrationSpec {

	def setup() {
		// A bit ugly, but lets clear db first
		Person.list()*.id.each {
			Person.get(it).delete()
		}
		Ship.list()*.id.each {
			Ship.get(it).delete(flush: true)
		}
	}

	// tag::add-to[]
	void "Test we can add a crewmember to a ship"() {
		setup:
		Ship ship = new Ship(name: 'Battlestar Galactica', crewsize: 1337, shiptype: Shiptype.MILITARY, productionDate: new Date()).save(failOnError: true)

		when:
		Person person = new Person(name: 'William Adama')
		ship.addToCrewmembers(person)

		then:
		!ship.hasErrors()
		Person.list().size() == 1
		Person.first().homeShip == ship
		Ship.first().crewmembers
		Ship.first().crewmembers.size() == 1
		Ship.first().crewmembers.first().name == 'William Adama'

		when: 'Show cascading when deleting ship'
		ship.delete(flush: true)

		then: 'The person is also deleted'
		Person.list().size() == 0
	}
	// end::add-to[]


	// tag::add-to2[]
	void "Test we can add a crewmember with a map"() {
		setup:
		Ship ship = new Ship(name: 'Battlestar Galactica', crewsize: 1337, shiptype: Shiptype.MILITARY, productionDate: new Date()).save(failOnError: true)

		when:
		ship.addToCrewmembers(name: 'Kara Trace')
		ship.addToCrewmembers(name: 'Lee Adama')
		ship.addToCrewmembers(name: 'Sharon Agathon')

		then:
		!ship.hasErrors()
		Person.list().size() == 3
		Ship.first().crewmembers.size() == 3
		Ship.first().crewmembers*.name.sort() == ['Kara Trace', 'Lee Adama', 'Sharon Agathon']
	}
	// end::add-to2[]



}
