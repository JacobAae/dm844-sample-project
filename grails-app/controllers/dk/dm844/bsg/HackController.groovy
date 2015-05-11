package dk.dm844.bsg

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityService

import grails.plugin.springsecurity.annotation.Secured


@Secured(['ROLE_DEMO'])
class HackController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    SpringSecurityService springSecurityService

    def index() {
        SecUser secUser = springSecurityService.currentUser
        List<Hack> hacks = Hack.findAllByOwner(secUser)
        List<UuidHack> uuidHacks = UuidHack.findAllByOwner(secUser)
        [secUser: secUser, hacks: hacks, uuidHacks: uuidHacks]
    }

    // Try searching for this:     0' OR 'a' = 'a
    def search(String term) {
        withForm {

        String query = "from Hack WHERE value = '${term}' "
        List<Hack> hacks = Hack.executeQuery(query)
//            List<Hack> hacks = Hack.executeQuery("from Hack WHERE value = ? ", term)

            render "Term: $term <br> hacks: $hacks"
        }.invalidToken {
            render "withToken says your token is invalid!"
        }
    }

    def show(Hack hack) {
        [hack: hack]
    }

    def showSecured(Hack hack) {
        render( view: 'show', model: [hack: hack] )
    }

    def showUuidhack(String id) {
        UuidHack uuidHack = UuidHack.findByUuid(id)
        [uuidHack: uuidHack]
    }

    def showdemo1(Hack hack) {
        // Using ${hack.value} in gsp
        [hack: hack]
    }
    def showdemo2(Hack hack) {
        // Using field in gsp
        Hack hack2 = Hack.get(hack.id)
        [hack: hack]
    }
    def showdemo3(Hack hack) {
        // Using hack.value in taglib with correct encoding
        [hack: hack]
    }

    def showdemo4(Hack hack) {
        // Using hack.value in taglib with incorrect encoding
        [hack: hack]
    }

    def showdemo5(Hack hack) {
        // Using hack.value with raw encoding in gsp
        [hack: hack]
    }

    def showdemo6(Hack hack) {
        // Mixing html and db output in taglib
        [hack: hack]
    }

    def showdemo7(Hack hack) {
        // MarkupBuilder in taglib
        [hack: hack]
    }

    def bindDemoBAd() {
        Hack hack = new Hack(params)
        render hack as JSON
    }

    def bindDemoGood() {
        println params
        Hack hack = new Hack()
        bindData(hack, params, exclude: ['owner'])
//        bindData(hack, params, ['owner'])
        render hack as JSON
    }


}
