package com.creditas.aspectorientedprogramming.core.crosscutting.exception.error

data class ExceptionError private constructor(
    val code: String,
    val message: String,
) {
    companion object {
        fun create(code: String, message: String) = ExceptionError(code = code, message = message)
    }
}