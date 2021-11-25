package com.creditas.aspectorientedprogramming.core.crosscutting.exception.error

data class Errors(private var _exceptionErrors: MutableSet<ExceptionError> = mutableSetOf()) {
    val exceptionErrors: MutableSet<ExceptionError>
        get() = this._exceptionErrors

    fun addError(exceptionError: ExceptionError) {
        this._exceptionErrors.add(exceptionError)
    }

    fun clearErrors() {
        this._exceptionErrors = mutableSetOf()
    }
}