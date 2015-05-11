package dk.dm844.bsg

class UuidHack {

    String uuid = UUID.randomUUID()
    String value
    SecUser owner

    static constraints = {
        uuid blank: false
        owner nullable: false
        value blank: false
    }
}
