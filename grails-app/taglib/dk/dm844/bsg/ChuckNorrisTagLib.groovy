package dk.dm844.bsg

import groovy.json.JsonSlurper

class ChuckNorrisTagLib {
    static defaultEncodeAs = [taglib: 'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def randomChuckNorrisJoke = {
        String json = "http://api.icndb.com/jokes/random".toURL().text

        JsonSlurper slurper = new JsonSlurper()
        Map jokeMap = slurper.parseText(json)

        out << jokeMap.value.joke
    }


}
