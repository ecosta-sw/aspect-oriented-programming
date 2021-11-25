package com.creditas.aspectorientedprogramming.infrastructure.repositories

import com.creditas.aspectorientedprogramming.infrastructure.entities.Person
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IPersonRepository: JpaRepository<Person, UUID> {
}