package dk.dm844.bsg

import groovy.xml.MarkupBuilder

class HackTagLib {
    static defaultEncodeAs = [taglib:'html']
    static encodeAsForTags = [
            displayHackGood: [taglib:'html'],
            displayHackBad: [taglib:'none'],
            displayHackMixed: [taglib:'none'],
            displayHackWithMarkupbuilder: [taglib:'none']]


    def displayHackGood = { attrs , body ->
        Hack hack = attrs.hack
        out << "The value is ${hack?.value}"
    }

    def displayHackBad = { attrs , body ->
        Hack hack = attrs.hack
        out << "The value is ${hack?.value}"
    }

    def displayHackMixed = { attrs , body ->
        Hack hack = attrs.hack
        out << "The value is <span style='color: red;'>${hack?.value.encodeAsHTML()}</span>"
    }

    def displayHackWithMarkupbuilder = { attrs , body ->
        Hack hack = attrs.hack
        MarkupBuilder mb = new MarkupBuilder(out)

        mb.h3 {
            mb.yield "The value is"
            span(style:'color: red') {
                mb.yield(hack?.value, true)
            }
        }
    }

}
