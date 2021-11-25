package com.creditas.aspectorientedprogramming.infrastructure.entities

import com.creditas.aspectorientedprogramming.infrastructure.util.database.ColumnNames
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
@Table(name = "COMPANIES")
data class Company(
    @Column(name = "FANTASY_NAME")
    val fantasyName: String,
    @Column(name = "SOCIAL_REASON")
    val socialReason: String
) {
    @Id
    @GeneratedValue(generator = "UUID")
    val id: UUID? = null

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP", name = ColumnNames.CREATED_AT, nullable = false, updatable = false)
    val createdAt: LocalDateTime? = null

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP", name = ColumnNames.UPDATED_AT, nullable = false)
    val updatedAt: LocalDateTime? = null
}
