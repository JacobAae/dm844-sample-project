package pages.ship

import geb.Page
import modules.ship.ShipFormModule
import modules.ship.ShipInfoModule

class EditPage extends Page {

    static url = "bsg/ship/edit"

    static at = {
        title ==~ /Edit Ship/
    }

    static content = {
//      form{ module ShipFormModule, $('form') } // Like this, the module does not need a base
        form { module ShipFormModule }
        submitButton { $('input', type: 'submit') }
    }
}
