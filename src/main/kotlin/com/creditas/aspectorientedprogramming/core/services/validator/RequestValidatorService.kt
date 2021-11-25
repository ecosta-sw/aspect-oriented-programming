package com.creditas.aspectorientedprogramming.core.services.validator

import com.creditas.aspectorientedprogramming.application.controllers.company.resources.request.CompanyRequest
import com.creditas.aspectorientedprogramming.application.controllers.person.resources.request.PersonRequest
import com.creditas.aspectorientedprogramming.core.services.company.CompanyService
import com.creditas.aspectorientedprogramming.core.services.person.PersonService
import org.springframework.stereotype.Service

@Service
class RequestValidatorService(private val companyService: CompanyService, private val personService: PersonService) {
    fun validateRequest(request: Any) {
        when (request) {
            is PersonRequest -> personService.validateRequest(request)
            else -> companyService.validateRequest(request as CompanyRequest)
        }

    }
}
