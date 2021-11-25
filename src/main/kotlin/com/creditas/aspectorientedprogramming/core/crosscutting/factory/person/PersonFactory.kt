package com.creditas.aspectorientedprogramming.core.crosscutting.factory.person

import com.creditas.aspectorientedprogramming.application.controllers.person.resources.request.PersonRequest
import com.creditas.aspectorientedprogramming.application.controllers.person.resources.response.PersonResponse
import com.creditas.aspectorientedprogramming.core.crosscutting.localdate.age
import com.creditas.aspectorientedprogramming.infrastructure.entities.Person

object PersonFactory {
    fun toPerson(request: PersonRequest): Person {
        return Person(
            birthDate = request.birthDate,
            firstName = request.firstName,
            lastName = request.lastName
        )
    }

    fun toPersonResponse(person: Person): PersonResponse {
        return PersonResponse(
            id = person.id!!,
            birthDate = person.birthDate,
            firstName = person.firstName,
            lastName = person.lastName,
            age = person.birthDate.age(),
        )
    }
}