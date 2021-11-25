package com.creditas.aspectorientedprogramming.application.commons.resources

data class ResponseDataTO private constructor(override val code: String, val data: Any) : ResponseTO() {
    companion object {
        fun create(code: String, data: Any): ResponseDataTO {
            return ResponseDataTO(code, data)
        }
    }
}
