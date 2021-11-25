package com.creditas.aspectorientedprogramming.application.controllers.person.resources.response

import java.time.LocalDate
import java.util.UUID

data class PersonResponse(
    val id: UUID,
    val birthDate: LocalDate,
    val firstName: String,
    val lastName: String,
    val age: Short
)
