package com.creditas.aspectorientedprogramming.core.services.person

import com.creditas.aspectorientedprogramming.application.controllers.person.resources.request.PersonRequest
import com.creditas.aspectorientedprogramming.core.crosscutting.exception.error.Errors
import com.creditas.aspectorientedprogramming.core.crosscutting.exception.person.InvalidPersonException
import com.creditas.aspectorientedprogramming.core.services.AbstractService
import com.creditas.aspectorientedprogramming.infrastructure.entities.Person
import com.creditas.aspectorientedprogramming.infrastructure.repositories.IPersonRepository
import com.creditas.aspectorientedprogramming.infrastructure.util.MessageUtil
import java.time.LocalDate
import java.util.Objects
import org.springframework.stereotype.Service

@Service
class PersonService(private val repository: IPersonRepository) : AbstractService() {
    override val errors: Errors = initializeErrors()

    fun save(person: Person): Person {
        return repository.save(person)
    }

    fun validateRequest(request: PersonRequest) {
        errors.clearErrors()
        validateBirthDate(request.birthDate)
        validateFirstName(request.firstName)
        validateLastName(request.lastName)

        throwErrors(InvalidPersonException(errors.exceptionErrors))
    }

    private fun validateBirthDate(birthDate: LocalDate) {
        if (Objects.isNull(birthDate)) {
            errors.addError(createError(MessageUtil.MESSAGE_0003))
        }
    }

    private fun validateFirstName(firstName: String) {
        if (firstName.isBlank()) {
            errors.addError(createError(MessageUtil.MESSAGE_0001))
        }
    }

    private fun validateLastName(lastName: String) {
        if (lastName.isBlank()) {
            errors.addError(createError(MessageUtil.MESSAGE_0002))
        }
    }

}