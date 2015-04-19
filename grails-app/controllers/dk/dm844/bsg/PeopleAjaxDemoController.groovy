package dk.dm844.bsg

import grails.converters.JSON
import org.apache.http.HttpStatus

class PeopleAjaxDemoController {

//	static allowedMethods = [ quote: 'GET']

	QuoteService quoteService
	PersonService personService

	def index() {
		[people: Person.list()]
	}

	def quote() {
		render( quoteService.randomQuote )
	}

	def quoteJson() {
		Map result = [
			quote: 	quoteService.randomQuote,
			status: 'You received a quote'
		]
		Thread.sleep(3000) // Fake calculation takes 3 seconds
//		response.sendError(HttpStatus.SC_UNAUTHORIZED, 'For some reason, you are not allowed to call this method :(')
		render( result as JSON )
	}

	def updateTitle(Long id, String title) {
		Person person = personService.updateTitle(id, title)
		render(template: 'person', model: [person: person])
	}

}
