package com.creditas.aspectorientedprogramming.application.controllers.person.resources.request

import java.time.LocalDate

data class PersonRequest(val birthDate: LocalDate, val firstName: String, val lastName: String)
