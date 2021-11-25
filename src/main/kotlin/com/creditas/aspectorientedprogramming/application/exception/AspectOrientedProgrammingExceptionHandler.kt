package com.creditas.aspectorientedprogramming.application.exception

import com.creditas.aspectorientedprogramming.application.commons.resources.ResponseTO
import com.creditas.aspectorientedprogramming.core.crosscutting.exception.AspectOrientedProgrammingException
import com.creditas.aspectorientedprogramming.core.services.errorhandler.ExceptionHandlerService
import org.hibernate.exception.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class AspectOrientedProgrammingExceptionHandler(private val service: ExceptionHandlerService) :
    ResponseEntityExceptionHandler() {

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(ex: ConstraintViolationException): ResponseEntity<ResponseTO> {
        val body = service.createError(service.createError(HttpStatus.CONFLICT.name, ex.localizedMessage))
        return ResponseEntity(body, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(AspectOrientedProgrammingException::class)
    fun handlePersonalFinanceException(ex: AspectOrientedProgrammingException): ResponseEntity<ResponseTO> {
        return ResponseEntity(service.createError(ex), ex.status())
    }
}