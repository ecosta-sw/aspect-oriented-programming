package com.creditas.aspectorientedprogramming.infrastructure.repositories

import com.creditas.aspectorientedprogramming.infrastructure.entities.Company
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ICompanyRepository : JpaRepository<Company, UUID>