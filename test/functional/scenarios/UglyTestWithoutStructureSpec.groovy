package scenarios


import geb.navigator.Navigator
import geb.spock.GebReportingSpec
import spock.lang.Stepwise

/**
 * This is just a demo of some of the possibilities of Geb, and in no way a pretty exampe of how to do it.
 *
 * You should use Pages and Modules, which cleans up the tests significantly
 */
@Stepwise
class UglyTestWithoutStructureSpec extends GebReportingSpec {

    void "Goto list of ships page"() {
        when:
        go 'http://localhost:8088/bsg/ship/index'

        then:
        $('h1').text() == 'The Fleet'
    }

    void "Edit link brings me to edit page"() {
        when: 'Click on the edit link for Colonial One'
        Navigator dd = $('dd', text: 'Colonial One')
        Navigator article = dd.closest("article")
        Navigator link = article.find("a")

        and:
        link.click()

        then:
        $('h1').text() == 'Edit Ship'
    }

    void "Update category"() {
        when:
        $('#shiptype').value('FREIGHT')
        $('input', type: 'submit').click()

        then:
        $('h1').text() == 'Show Ship'
    }

    void "Check new value at ship list page"() {
        when:
        go 'http://localhost:8088/bsg/ship/index'

        and:
        Navigator dd = $('dd', text: 'Colonial One')
        Navigator article = dd.closest("article")

        then:
        article.find('dd', text: 'FREIGHT')
    }

}