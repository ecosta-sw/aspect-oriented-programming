package com.creditas.aspectorientedprogramming.core.services.company

import com.creditas.aspectorientedprogramming.application.controllers.company.resources.request.CompanyRequest
import com.creditas.aspectorientedprogramming.core.crosscutting.exception.error.Errors
import com.creditas.aspectorientedprogramming.core.crosscutting.exception.person.InvalidPersonException
import com.creditas.aspectorientedprogramming.core.services.AbstractService
import com.creditas.aspectorientedprogramming.infrastructure.entities.Company
import com.creditas.aspectorientedprogramming.infrastructure.repositories.ICompanyRepository
import com.creditas.aspectorientedprogramming.infrastructure.util.MessageUtil
import org.springframework.stereotype.Service

@Service
class CompanyService(val repository: ICompanyRepository) : AbstractService() {

    override val errors: Errors = initializeErrors()

    fun save(company: Company): Company {
        return repository.save(company)
    }

    fun validateRequest(request: CompanyRequest) {
        errors.clearErrors()
        validateFantasyName(request.fantasyName)
        validateSocialReason(request.socialReason)

        throwErrors(InvalidPersonException(errors.exceptionErrors))
    }

    private fun validateFantasyName(firstName: String) {
        if (firstName.isBlank()) {
            errors.addError(createError(MessageUtil.MESSAGE_0004))
        }
    }

    private fun validateSocialReason(lastName: String) {
        if (lastName.isBlank()) {
            errors.addError(createError(MessageUtil.MESSAGE_0005))
        }
    }
}