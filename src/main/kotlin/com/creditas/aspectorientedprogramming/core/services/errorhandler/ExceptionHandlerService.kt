package com.creditas.aspectorientedprogramming.core.services.errorhandler

import com.creditas.aspectorientedprogramming.core.crosscutting.exception.error.Errors
import com.creditas.aspectorientedprogramming.core.services.AbstractService
import org.springframework.stereotype.Service

@Service
class ExceptionHandlerService : AbstractService() {
    override val errors: Errors = Errors()
}