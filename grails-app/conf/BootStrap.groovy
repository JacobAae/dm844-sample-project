import dk.dm844.bsg.Person
import dk.dm844.bsg.Ship
import dk.dm844.bsg.Shiptype

class BootStrap {

    def init = { servletContext ->

        development {
            createData()
	        Ship bsg = Ship.findByName('Battlestar Galactica')
	        ['Starbuck', 'Lee Adama', 'William Adama', 'Saul Tigh'].each {
		        bsg.addToCrewmembers(new Person(name: it))
		        bsg.save()
	        }

        }
        test {
            createData()
        }
    }
    def destroy = {
    }

    private createData() {
        [
                ['Battlestar Galactica', 1337, Shiptype.MILITARY],
                ['Battlestar Pegasus', 2143, Shiptype.MILITARY],
                ['Colonial One', 142, Shiptype.ADMINISTRATION],
                ['Astral Queen', 564, Shiptype.ACCOMODATION],
                ['Cloud 9', 6542, Shiptype.ACCOMODATION]

        ].each {
            new Ship(name: it[0], crewsize: it[1], shiptype: it[2], description: 'N/A', productionDate: new Date()).save(failOnError: true)
        }
    }
}
