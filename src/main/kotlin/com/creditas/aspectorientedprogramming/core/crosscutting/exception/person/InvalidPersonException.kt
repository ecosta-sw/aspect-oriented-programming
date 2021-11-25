package com.creditas.aspectorientedprogramming.core.crosscutting.exception.person

import com.creditas.aspectorientedprogramming.core.crosscutting.exception.AspectOrientedProgrammingException
import com.creditas.aspectorientedprogramming.core.crosscutting.exception.error.ExceptionError
import org.springframework.http.HttpStatus

class InvalidPersonException(override val errors: MutableSet<ExceptionError>) : AspectOrientedProgrammingException() {
    override fun status(): HttpStatus = HttpStatus.UNPROCESSABLE_ENTITY
}
