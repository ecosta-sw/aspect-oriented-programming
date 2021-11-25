package com.creditas.aspectorientedprogramming.core.services.validator

import com.creditas.aspectorientedprogramming.application.controllers.person.resources.request.PersonRequest
import com.creditas.aspectorientedprogramming.core.services.person.PersonService
import org.springframework.stereotype.Service

@Service
class RequestValidatorService(private val personService: PersonService) {
    fun validateRequest(request: Any) {
        personService.validateRequest(request as PersonRequest)
    }
}
