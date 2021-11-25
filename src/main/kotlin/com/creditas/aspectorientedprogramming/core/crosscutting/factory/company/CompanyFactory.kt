package com.creditas.aspectorientedprogramming.core.crosscutting.factory.company

import com.creditas.aspectorientedprogramming.application.controllers.company.resources.request.CompanyRequest
import com.creditas.aspectorientedprogramming.application.controllers.company.resources.response.CompanyResponse
import com.creditas.aspectorientedprogramming.infrastructure.entities.Company

object CompanyFactory {
    fun toCompany(request: CompanyRequest): Company {
        return Company(fantasyName = request.fantasyName, socialReason = request.socialReason)
    }

    fun toCompanyResponse(company: Company): CompanyResponse {
        return CompanyResponse.create(company)
    }
}