package dk.dm844.bsg

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_DEMO'])
class PersonController {

	static scaffold = true

}
