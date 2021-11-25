package com.creditas.aspectorientedprogramming.core.services

import com.creditas.aspectorientedprogramming.application.commons.resources.ResponseErrorTO
import com.creditas.aspectorientedprogramming.application.commons.resources.ResponseTO
import com.creditas.aspectorientedprogramming.core.crosscutting.exception.AspectOrientedProgrammingException
import com.creditas.aspectorientedprogramming.core.crosscutting.exception.error.Errors
import com.creditas.aspectorientedprogramming.core.crosscutting.exception.error.ExceptionError
import com.creditas.aspectorientedprogramming.infrastructure.util.MessageUtil
import org.springframework.http.HttpStatus

abstract class AbstractService {
    protected abstract val errors: Errors

    protected fun throwErrors(ex: AspectOrientedProgrammingException) {
        if (ex.errors.isNotEmpty()) {
            throw ex
        }
    }

    fun initializeErrors(): Errors {
        return Errors()
    }


    fun createError(ex: AspectOrientedProgrammingException): ResponseTO {
        return buildError(ex)
    }

    fun createError(message: String, code: String = HttpStatus.BAD_REQUEST.name): ExceptionError {
        return buildError(code, message)
    }

    fun createError(error: ExceptionError, code: String = HttpStatus.BAD_REQUEST.name): ResponseTO {
        return buildError(code, error)
    }

    fun createError(code: String): ExceptionError {
        return buildError(code)
    }

    fun createErrors(error: ExceptionError): MutableSet<ExceptionError> {
        return mutableSetOf(error)
    }

    fun createErrors(errors: List<ExceptionError>): ResponseTO {
        return ResponseErrorTO.create(errors = errors, code = HttpStatus.UNPROCESSABLE_ENTITY.name)
    }

    fun createErrors(code: String, data: Any): MutableSet<ExceptionError> {
        val errors: MutableSet<ExceptionError> = mutableSetOf()
        errors.add(buildError(code, getMessageWithParam(code, data)))

        return errors
    }

    private fun buildError(code: String, message: String): ExceptionError {
        return ExceptionError.create(code, message)
    }

    private fun buildError(code: String, error: ExceptionError): ResponseTO {
        return ResponseErrorTO.create(errors = listOf(error), code = code)
    }

    private fun buildError(ex: AspectOrientedProgrammingException): ResponseTO {
        return ResponseErrorTO.create(errors = ex.errors.toList(), code = ex.status().name)
    }

    private fun buildError(code: String): ExceptionError {
        return ExceptionError.create(code, MessageUtil.getMessage(code))
    }

    private fun getMessageWithParam(code: String, data: Any): String {
        return MessageUtil.createMessageWithResourceBundle(code, data)
    }
}
