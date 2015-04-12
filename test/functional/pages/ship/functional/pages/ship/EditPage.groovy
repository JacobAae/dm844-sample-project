package pages.ship.functional.pages.ship

import geb.Page

class EditPage extends Page {

    static url = "ship/edit"

    static at = {
        title ==~ /Edit Ship/
    }

    static content = {
//      form{ module ShipFormModule, $('form') } // Like this, the module does not need a base
        form { module pages.ship.functional.modules.ship.ShipFormModule }
        submitButton { $('input', type: 'submit') }
    }
}
