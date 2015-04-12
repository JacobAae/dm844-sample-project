package pages.ship

import geb.Page
import modules.ship.ShipInfoModule

class ListPage extends Page {

    static url = "ship/list"

    static at = {
        title ==~ /The Fleet/
    }

    static content = {
        ships { moduleList ShipInfoModule, $('section.shiplist article')}
    }

    ShipInfoModule findModuleForName(String name) {
        ships.find{ it.name == name }
    }

}
