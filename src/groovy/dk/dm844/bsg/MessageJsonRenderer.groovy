package dk.dm844.bsg

import grails.rest.render.*
import groovy.json.JsonBuilder
import org.codehaus.groovy.grails.web.mime.MimeType
import org.springframework.http.HttpStatus

class MessageJsonRenderer  extends AbstractRenderer<Message> {

    MessageJsonRenderer() {
        super(Message, [MimeType.JSON,MimeType.TEXT_JSON] as MimeType[])
    }

    void render(Message object, RenderContext context) {
        context.contentType = MimeType.JSON.name
        context.status = HttpStatus.OK

        Writer writer = context.getWriter()
        Map content = [id: object.id, message:object.message, from: object.from.name]
        JsonBuilder builder = new JsonBuilder(content)
        builder.writeTo(writer)
        writer.flush()
    }
}

