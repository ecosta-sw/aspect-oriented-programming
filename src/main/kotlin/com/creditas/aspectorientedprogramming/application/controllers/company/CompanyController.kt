package com.creditas.aspectorientedprogramming.application.controllers.company

import com.creditas.aspectorientedprogramming.application.annotation.RequestValidator
import com.creditas.aspectorientedprogramming.application.commons.extension.APPLICATION_JSON_UTF8_VALUE
import com.creditas.aspectorientedprogramming.application.commons.resources.ResponseTO
import com.creditas.aspectorientedprogramming.application.controllers.company.resources.request.CompanyRequest
import com.creditas.aspectorientedprogramming.application.factory.ResponseTOFactory
import com.creditas.aspectorientedprogramming.core.crosscutting.factory.company.CompanyFactory
import com.creditas.aspectorientedprogramming.core.services.company.CompanyService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/company", produces = [APPLICATION_JSON_UTF8_VALUE])
class CompanyController(private val service: CompanyService) {

    @RequestValidator
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody request: CompanyRequest): ResponseEntity<ResponseTO> {
        val companyToBeCreate = CompanyFactory.toCompany(request)
        val createdCompany = service.save(companyToBeCreate)
        val response = CompanyFactory.toCompanyResponse(createdCompany)
        return ResponseEntity(ResponseTOFactory.success(response, HttpStatus.CREATED), HttpStatus.CREATED)
    }
}
