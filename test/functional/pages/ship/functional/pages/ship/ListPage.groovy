package pages.ship.functional.pages.ship

import geb.Page

class ListPage extends Page {

    static url = "ship/list"

    static at = {
        title ==~ /The Fleet/
    }

    static content = {
        ships { moduleList pages.ship.functional.modules.ship.ShipInfoModule, $('section.shiplist article')}
    }

    pages.ship.functional.modules.ship.ShipInfoModule findModuleForName(String name) {
        ships.find{ it.name == name }
    }

}
