package dk.dm844.bsg

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional

@Transactional
class SecurityCheckService {

    SpringSecurityService springSecurityService

    boolean allowedToSeeHack(Long id) {
        if( !id ) {
            return false
        }
        Hack hack = Hack.get(id)
        if( hack?.owner == springSecurityService.currentUser) {
            return true
        } else {
            false
        }


    }

}
