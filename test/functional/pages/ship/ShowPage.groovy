package pages.ship

import geb.Page

class ShowPage extends Page {

    static url = "ship/show"

    static at = {
        title ==~ /Show Ship/
    }

    static content = {
        // TODO Could delete or elements to check
    }
}
