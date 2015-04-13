package dk.dm844.bsg

class ShipController {

	static scaffold = true

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Ship.list(params), model:[shipInstanceCount: Ship.count()]
	}

}
