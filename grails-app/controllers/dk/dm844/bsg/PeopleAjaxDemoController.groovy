package dk.dm844.bsg

import grails.converters.JSON
import grails.converters.XML
import org.springframework.http.HttpStatus


class PeopleAjaxDemoController {

//	static allowedMethods = [ quote: 'GET']

	QuoteService quoteService
	PersonService personService

	def index() {
		[people: Person.list()]
	}

	def quote() {
		String quote = quoteService.randomQuote
		render( quote )
	}

	def quoteJson() {
		Map result = [
			quote: 	quoteService.randomQuote,
			status: 'You received a quote'
		]
		Thread.sleep(3000) // Fake calculation takes 3 seconds
		response.sendError(HttpStatus.UNAUTHORIZED, 'For some reason, you are not allowed to call this method :(')
		render( result as JSON )
	}

	def updateTitle(Long id, String title) {
		Person person = personService.updateTitle(id, title)
		render(template: 'person', model: [person: person])
	}

	def somePersonJson() {
		Person person = Person.list([sort:'lastUpdated', order:'desc',max:1]).first()
		String personHtml = g.render(template: 'person', model: [person: person])
		Map jsonMap = [
				id: person.id,
				quote: 	"<h2>${quoteService.randomQuote}</h2>",
		        lastUpdated: person.lastUpdated,
				personHtml: personHtml
		]
		render( jsonMap as JSON )
	}

	def personOutputDemo() {
		Person person = Person.list([sort:'lastUpdated', order:'desc',max:1]).first()

		withFormat {
			html { render(template: 'person', model: [person: person])}
			xml { render person as XML }
			json { render person as JSON }
		}
		// Or respond person - if all identical
	}

	def marshallerDemo() {
		JSON.registerObjectMarshaller(Person) {
			def map = [
			        name: it.name,
					updated: g.formatDate(date: it.lastUpdated, format: 'yyyy-MM-dd')
			]
			return map
		}
		render( Person.list() as JSON)
	}

	def angularDemo() {

	}

}
