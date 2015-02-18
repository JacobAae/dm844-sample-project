package dk.dm844.bsg


class Crisis {

	String header
	String description
	Boolean solved

	static hasMany = [solvers: Person, affectedShips: Ship]
	static belongsTo = [Person, Ship]

	static constraints = {
		header blank: false
		description nullable: true
	}

    String toString() {
        header
    }
}
