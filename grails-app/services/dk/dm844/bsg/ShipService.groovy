package dk.dm844.bsg

import grails.transaction.Transactional

@Transactional
class ShipService {

    List<Ship> findAllShips(int max = 10, String sortOrder = 'asc') {
        Ship.list([max: max, sort: 'name', order: sortOrder])
    }

}
