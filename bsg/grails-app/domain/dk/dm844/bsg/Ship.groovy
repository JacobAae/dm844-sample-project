package dk.dm844.bsg

class Ship {

	String name
	Shiptype shiptype
	Integer crewsize
	Date productionDate
	String description

	static hasMany = [crewmembers: Person, affectedBy: Crisis]

    static constraints = {
	    name unique: true, blank: false
	    crewsize min: 8
	    description nullable: true, blank: true
    }

    // tag::before-validate-method[]
    def beforeValidate() {
        if( productionDate == null) {
            productionDate = new Date()
        }
    }
    // end::before-validate-method[]

    String toString() {
        name
    }

}
