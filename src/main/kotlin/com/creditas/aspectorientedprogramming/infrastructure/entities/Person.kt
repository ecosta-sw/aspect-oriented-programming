package com.creditas.aspectorientedprogramming.infrastructure.entities

import com.creditas.aspectorientedprogramming.infrastructure.util.database.ColumnNames.BIRTH_DATE
import com.creditas.aspectorientedprogramming.infrastructure.util.database.ColumnNames.CREATED_AT
import com.creditas.aspectorientedprogramming.infrastructure.util.database.ColumnNames.FIRST_NAME
import com.creditas.aspectorientedprogramming.infrastructure.util.database.ColumnNames.LAST_NAME
import com.creditas.aspectorientedprogramming.infrastructure.util.database.ColumnNames.UPDATED_AT
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp

@Entity
@Table(name = "PEOPLE")
data class Person(
    @Column(name = BIRTH_DATE)
    val birthDate: LocalDate,
    @Column(name = FIRST_NAME)
    val firstName: String,
    @Column(name = LAST_NAME)
    val lastName: String
) {

    @Id
    @GeneratedValue(generator = "UUID")
    val id: UUID? = null

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP", name = CREATED_AT, nullable = false, updatable = false)
    val createdAt: LocalDateTime? = null

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP", name = UPDATED_AT, nullable = false)
    val updatedAt: LocalDateTime? = null


}