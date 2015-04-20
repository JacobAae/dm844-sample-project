package dk.dm844.bsg

import grails.rest.Resource

@Resource(formats = ['json'])
class Message {

	Person from
	String message

	Date dateCreated

	static constraints = {
			message blank: false
	}
}
