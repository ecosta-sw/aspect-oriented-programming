package com.creditas.aspectorientedprogramming.application.controllers.person

import com.creditas.aspectorientedprogramming.application.annotation.RequestValidator
import com.creditas.aspectorientedprogramming.application.commons.extension.APPLICATION_JSON_UTF8_VALUE
import com.creditas.aspectorientedprogramming.application.commons.resources.ResponseTO
import com.creditas.aspectorientedprogramming.application.controllers.person.resources.request.PersonRequest
import com.creditas.aspectorientedprogramming.application.factory.ResponseTOFactory
import com.creditas.aspectorientedprogramming.core.crosscutting.factory.PersonFactory
import com.creditas.aspectorientedprogramming.core.services.person.PersonService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person", produces = [APPLICATION_JSON_UTF8_VALUE])
class PersonController(private val service: PersonService) {

    @RequestValidator
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody request: PersonRequest): ResponseEntity<ResponseTO> {
        val personToBeSave = PersonFactory.toPerson(request)
        val savedPerson = service.save(personToBeSave)
        val response = PersonFactory.toPersonResponse(savedPerson)
        return ResponseEntity(ResponseTOFactory.success(response, HttpStatus.CREATED), HttpStatus.CREATED)

    }
}