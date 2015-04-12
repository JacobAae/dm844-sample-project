package pages.ship.functional.modules.ship

import geb.Module

class ShipInfoModule extends Module {

    static content = {
        dds { $("dd", it) }
        name { dds(0).text() }
        shiptype{ dds(1).text() }
        crewsize{ dds(2).text() }
        // Could add more here
        editlink { $("a") }
    }
}
