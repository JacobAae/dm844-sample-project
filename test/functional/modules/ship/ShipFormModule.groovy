package modules.ship

import geb.Module

class ShipFormModule extends Module {

    static base = { $('form') }

    static content = {
        nameInput { $('#name').value() }
        shiptypeInput { $('#name').value() }
    }
}
