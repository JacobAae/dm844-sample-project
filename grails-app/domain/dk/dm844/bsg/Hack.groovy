package dk.dm844.bsg

class Hack {

    String value
    SecUser owner

    static constraints = {
        owner nullable: false
        value blank: false
    }
}
