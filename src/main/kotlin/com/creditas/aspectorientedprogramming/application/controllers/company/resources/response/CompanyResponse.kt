package com.creditas.aspectorientedprogramming.application.controllers.company.resources.response

import com.creditas.aspectorientedprogramming.infrastructure.entities.Company
import java.util.UUID

data class CompanyResponse private constructor(val id: UUID, val fantasyName: String, val socialReason: String) {
    companion object {
        fun create(company: Company): CompanyResponse {
            return CompanyResponse(
                id = company.id!!,
                fantasyName = company.fantasyName,
                socialReason = company.socialReason
            )
        }
    }
}