import dk.dm844.bsg.Message
import dk.dm844.bsg.Hack
import dk.dm844.bsg.Person
import dk.dm844.bsg.SecRole
import dk.dm844.bsg.SecUser
import dk.dm844.bsg.SecUserSecRole
import dk.dm844.bsg.Ship
import dk.dm844.bsg.Shiptype
import dk.dm844.bsg.UuidHack

class BootStrap {

    def init = { servletContext ->

        development {
            createData()
	        Ship bsg = Ship.findByName('Battlestar Galactica')
	        ['Starbuck', 'Lee Adama', 'William Adama', 'Saul Tigh'].each {
                Person person = new Person(name: it)
		        bsg.addToCrewmembers(person)
		        bsg.save(flush: true)

                person.save(flush: true)
                Message message = new Message(from: person, message: "Hi I'm ${person.name}")
                message.save(failOnError: true)
	        }

        }
        test {
            createData()
        }
        production {

        }

        resetHacks()
        createUsersAndRoles()

    }
    def destroy = {
    }

    private resetHacks() {
        List ids = Hack.all*.id
        ids.each {
            Hack.get(it).delete()
        }
        ids = UuidHack.all*.id
        ids.each {
            UuidHack.get(it).delete()
        }
        SecRole.all*.id.each {
            SecRole.get(it).delete()
        }
        SecUser.all*.id.each {
            SecUser.get(it).delete()
        }
    }

    private createUsersAndRoles() {

        SecRole secRole = new SecRole(authority: 'ROLE_DEMO')
        secRole.save(failOnError: true)
        Random random = new Random()

        15.times {
            SecUser secUser = new SecUser(username: "group-${it}", password: "group-${it}" ).save(failOnError: true)
            SecUserSecRole.create(secUser, secRole, true)

            Hack hack = new Hack(value: "Hack value for group ${it}: ${random.nextInt(1000)}", owner: secUser)
            hack.save(failOnError: true)

            UuidHack uuidHackhack = new UuidHack(value: "Hack value for group ${it}: ${random.nextInt(1000)}", owner: secUser)
            uuidHackhack.save(failOnError: true)
        }

        Hack javascript = new Hack(value: '<script>alert("Hello XSS");</script>', owner: SecUser.findByUsername('group-0'))
        javascript.save(failOnError: true)

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
