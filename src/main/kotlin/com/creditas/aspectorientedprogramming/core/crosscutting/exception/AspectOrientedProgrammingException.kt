package com.creditas.aspectorientedprogramming.core.crosscutting.exception

import com.creditas.aspectorientedprogramming.core.crosscutting.exception.error.ExceptionError
import org.springframework.http.HttpStatus

abstract class AspectOrientedProgrammingException : RuntimeException() {

    abstract val errors: MutableSet<ExceptionError>
    abstract fun status(): HttpStatus
}