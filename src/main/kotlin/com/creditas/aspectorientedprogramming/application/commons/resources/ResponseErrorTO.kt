package com.creditas.aspectorientedprogramming.application.commons.resources

import com.creditas.aspectorientedprogramming.core.crosscutting.exception.error.ExceptionError

data class ResponseErrorTO private constructor(override val code: String, val errors: List<ExceptionError>) :
    ResponseTO() {
    companion object {
        fun create(code: String, errors: List<ExceptionError>): ResponseErrorTO {
            return ResponseErrorTO(code, errors)
        }
    }
}