package dk.dm844.bsg

class SecurityFilters {

    SecurityCheckService securityCheckService

    def filters = {
        showHack(controller:'hack', action:'showSecured') {
            before = {
                if( securityCheckService.allowedToSeeHack(params.long('id'))) {
                    return true
                } else {
                    response.sendError(403, 'That is not for you to see!')
                    return false
                }
            }
        }
    }
}
