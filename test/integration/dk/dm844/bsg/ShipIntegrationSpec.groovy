package dk.dm844.bsg

import grails.test.spock.IntegrationSpec

class ShipIntegrationSpec extends IntegrationSpec {

	def setup() {
		// A bit ugly, but lets clear db first of any ships
		Ship.list()*.id.each {
			Ship.get(it).delete()
		}
	}

	// tag::save-get[]
	void "Test saving and retrieving a Ship"() {
		given:
		Ship ship = fullyPopulatedShip

		expect:
		ship.validate()

		when:
		ship.save()

		then:
		Ship.get(ship.id).name == 'X-Battlestar Galactica'
	}
	// end::save-get[]

	// tag::list[]
	void "Test list of all Ships"() {
		given:
		createShipsInTheDatabase()

		when:
		List<Ship> shipList = Ship.list()

		then:
		shipList
		shipList.size() == 5
		shipList*.crewsize.sort() == [142, 564, 1337, 2143, 6542]

	}
	// end::list[]



	// tag::findBy[]
	void "Test the findBy functionality"() {
		setup:
		createShipsInTheDatabase()

		when:
		Ship ship = Ship.findByName('X-Cloud 9')

		then:
		ship
		ship.crewsize == 6542
	}
	// end::findBy[]

	// tag::findAllBy[]
	void "Test the findAllBy functionality"() {
		setup:
		createShipsInTheDatabase()

		when:
		List<Ship> ships = Ship.findAllByShiptype( Shiptype.MILITARY )

		then:
		ships
		ships.size() == 2
		ships*.name.containsAll(['X-Battlestar Galactica', 'X-Battlestar Pegasus'])
	}
	// end::findAllBy[]


	// tag::findAllBy2[]
	void "Test the findAllBy functionality with multiple criterias"() {
		setup:
		createShipsInTheDatabase()

		when:
		List<Ship> ships = Ship.findAllByShiptypeAndNameIlike( Shiptype.MILITARY , '%star%')

		then:
		ships
		ships.size() == 2
		ships*.name.containsAll(['X-Battlestar Galactica', 'X-Battlestar Pegasus'])
	}
	// end::findAllBy2[]

	// tag::where-query[]
	void "Test a where query returning unique element"() {
		setup:
		createShipsInTheDatabase()

		when:
		def query = Ship.where {
			crewsize >= max(crewsize)
		}
		Ship largestCrew = query.find()

		then:
		largestCrew
		largestCrew.name == 'X-Cloud 9'
		largestCrew == Ship.list( max:1, sort: 'crewsize', order: 'desc').first()
	}
	// end::where-query[]


	// tag::where-query2[]
	void "Test a where query returning multiple elements"() {
		setup:
		createShipsInTheDatabase()

		when:
		def query = Ship.where {
			shiptype == Shiptype.ACCOMODATION
		}
		List<Ship> ships = query.list()

		then:
		ships
		ships.size() == 2
		ships*.name.containsAll(['X-Astral Queen', 'X-Cloud 9'])
	}
	// end::where-query2[]

	// tag::criteria-query[]
	void "Test a criteria query"() {
		setup:
		createShipsInTheDatabase()

		when:
		def criteria = Ship.createCriteria()
		List<Ship> ships = criteria.list {
			between('crewsize', 100, 600)
			inList('shiptype', [Shiptype.ADMINISTRATION, Shiptype.ACCOMODATION])
			maxResults(5)
			order('name', 'asc')
		}

		then:
		ships
		ships.size() == 2
		ships*.name == ['X-Astral Queen', 'X-Colonial One']
	}
	// end::criteria-query[]


	// tag::hql-query[]
	void "Test a HQL query"() {
		setup:
		createShipsInTheDatabase()

		when:
		List<Ship> ships = Ship.executeQuery('from Ship order by crewsize desc', [max: 2])

		then:
		ships
		ships.size() == 2
		ships*.name == ['X-Cloud 9', 'X-Battlestar Pegasus']

	}
	// end::hql-query[]

    // tag::before-validate[]
    void "Test before validate populates with productionDate"() {
        setup:
        Ship ship = fullyPopulatedShip

        when:
        ship.productionDate = null

        boolean validate = ship.validate()

        then:
        validate
        ship.productionDate

    }
    // end::before-validate[]

    // tag::getFullyPopulatedShip[]
	Ship getFullyPopulatedShip() {
		new Ship(
				name: "X-Battlestar Galactica",
				crewsize: 1337,
				productionDate: new Date(),
				description: "Military headquarter",
				shiptype: Shiptype.MILITARY
		)
	}
	// end::getFullyPopulatedShip[]

	// tag::db-ships[]
	private createShipsInTheDatabase() {
		[
		        ['X-Battlestar Galactica', 1337, Shiptype.MILITARY],
		        ['X-Battlestar Pegasus', 2143, Shiptype.MILITARY],
		        ['X-Colonial One', 142, Shiptype.ADMINISTRATION],
		        ['X-Astral Queen', 564, Shiptype.ACCOMODATION],
		        ['X-Cloud 9', 6542, Shiptype.ACCOMODATION]

		].each {
			new Ship(name: it[0], crewsize: it[1], shiptype: it[2], description: 'N/A', productionDate: new Date()).save(failOnError: true)
		}
	}
	// end::db-ships[]

}
