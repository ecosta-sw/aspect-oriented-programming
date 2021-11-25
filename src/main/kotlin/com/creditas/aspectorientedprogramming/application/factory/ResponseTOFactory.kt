package com.creditas.aspectorientedprogramming.application.factory

import com.creditas.aspectorientedprogramming.application.commons.resources.ResponseDataTO
import com.creditas.aspectorientedprogramming.application.commons.resources.ResponseErrorTO
import com.creditas.aspectorientedprogramming.application.commons.resources.ResponseTO
import com.creditas.aspectorientedprogramming.core.crosscutting.exception.AspectOrientedProgrammingException
import com.creditas.aspectorientedprogramming.core.crosscutting.exception.error.ExceptionError
import org.springframework.http.HttpStatus

object ResponseTOFactory {
    fun success(data: Any, code: HttpStatus = HttpStatus.OK): ResponseTO {
        return ResponseDataTO.create(data = data, code = code.name)
    }

    fun createErrors(errors: List<ExceptionError>, code: String = HttpStatus.BAD_REQUEST.name): ResponseTO {
        return ResponseErrorTO.create(errors = errors, code = code)
    }

    fun createErrors(ex: AspectOrientedProgrammingException, code: String = HttpStatus.BAD_REQUEST.name): ResponseTO {
        return ResponseErrorTO.create(errors = ex.errors.toList(), code = code)
    }

    fun createError(ex: AspectOrientedProgrammingException): ResponseTO {
        return ResponseErrorTO.create(errors = ex.errors.toList(), code = ex.status().name)
    }

    fun createError(error: ExceptionError, code: String = HttpStatus.BAD_REQUEST.name): ResponseTO {
        return ResponseErrorTO.create(errors = listOf(error), code = code)
    }
}