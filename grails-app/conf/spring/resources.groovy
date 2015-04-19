import dk.dm844.bsg.Message
import grails.rest.render.hal.HalJsonCollectionRenderer
import grails.rest.render.json.JsonRenderer

beans = {

	messageRenderer( JsonRenderer, Message) {
		excludes = ['from']
	}

	messageCollectionRenderer( HalJsonCollectionRenderer, Message) {
		includes = ['from']
	}
}
