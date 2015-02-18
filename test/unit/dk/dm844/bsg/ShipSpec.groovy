package dk.dm844.bsg

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Ship)
class ShipSpec extends Specification {

    def setup() {
	    // Adds the validate method to the domain (or command object) class
	    mockForConstraintsTests(Ship)
    }

    def cleanup() {

    }

    void "Test fully populated ship is valid"() {
	    expect:
	    fullyPopulatedShip.validate()
    }

	@Unroll("Test name constraint where #description")
	void "Test name must be a nonempty String"() {
		setup:
		Ship ship = fullyPopulatedShip
		ship.name = name

		expect:
		!ship.validate()
		false

		where:
		name        | description
		null        | 'Name is null'
		''          | 'Name is the empty string'
		'    '      | 'Name is string with whitespace'
	}

	void "Test uniqueness of name"() {
		setup:
		Ship first = fullyPopulatedShip.save(failOnError: true, flush: true)

		when:
		Ship second = fullyPopulatedShip

		then:
		first.name == second.name
		first != second
		!second.validate()
		second.hasErrors()
	}

	void "Test crewsize constraint"() {
		when:
		Ship ship = fullyPopulatedShip
		ship.crewsize = 7

		then:
		!ship.validate()
		ship.errors.errorCount == 1
		ship.errors.getFieldError('crewsize').code == 'min.notmet'
	}

	void "Test description can be optional"() {
		when:
		Ship ship = fullyPopulatedShip
		ship.description = description

		then:
		ship.validate()

		where:
		description << [ null, '']
	}



	Ship getFullyPopulatedShip() {
		new Ship(
				name: "Battlestar Galactica",
				crewsize: 1337,
				productionDate: new Date(),
				description: "Military headquarter",
				shiptype: Shiptype.MILITARY
		)
	}

}
