package com.creditas.aspectorientedprogramming.application.exception

import com.creditas.aspectorientedprogramming.core.crosscutting.exception.AspectOrientedProgrammingException
import com.creditas.aspectorientedprogramming.core.crosscutting.exception.error.ExceptionError
import org.springframework.http.HttpStatus

class ProceedingJoinPointNotFoundException : AspectOrientedProgrammingException() {
    override val errors: MutableSet<ExceptionError> = mutableSetOf()

    override fun status(): HttpStatus {
        return HttpStatus.UNPROCESSABLE_ENTITY
    }
}