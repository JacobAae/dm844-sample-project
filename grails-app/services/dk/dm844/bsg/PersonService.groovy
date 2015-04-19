package dk.dm844.bsg

import grails.transaction.Transactional

@Transactional
class PersonService {

	Person updateTitle(Long id, String title) {
		Person person = Person.get(id)
		person.title = title
		person.save(failOnError: true)
		person
	}
}
