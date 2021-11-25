package com.creditas.aspectorientedprogramming.configuration.container

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.wait.strategy.Wait

@ExtendWith(PostgresDataBaseTestContainer::class)
annotation class PostgresDataBaseContainer

class PostgresDataBaseTestContainer : BeforeAllCallback, AfterAllCallback {

    companion object {
        const val POSTGRES_IMAGE_NAME = "postgres:12-alpine"
    }

    private val database = PostgreSQLContainer<Nothing>(POSTGRES_IMAGE_NAME)
        .apply {
            withExposedPorts(5434, 5432)
            withEnv("POSTGRES_DB", "aspect_oriented_programming_db_test")
            withEnv("POSTGRES_USER", "aspect_oriented_programming_test")
            withEnv("POSTGRES_PASSWORD", "BnBBwv8Zh86qdzMc")
            waitingFor(Wait.forLogMessage(".*ready to accept connections.*\\n", 1))
        }

    override fun beforeAll(context: ExtensionContext?) {
        startDatabase()
    }

    override fun afterAll(context: ExtensionContext?) {
        stopDatabase()
    }

    private fun startDatabase() {
        when {
            database.isRunning.not() -> {
                database.start().apply {
                    setDatabaseProperties()
                }
                println("\nStarting Postgres Database Container...\n")
            }
            else -> println("\nPostgres wasn't started because it is already running\n")
        }
    }

    private fun setDatabaseProperties() {
        System.setProperty("spring.datasource.url", database.jdbcUrl)
        println("\n\nTHE DATABASE URL IS: ${database.jdbcUrl}\n\n")
        System.setProperty("spring.datasource.username", database.username)
        println("\n\nTHE DATABASE USERNAME IS: ${database.username}\n\n")
        System.setProperty("spring.datasource.password", database.password)
        println("\n\nTHE DATABASE PASSWORD IS: ${database.password}\n\n")
    }

    private fun stopDatabase() {
        when {
            database.isRunning -> {
                println("\nStopping Postgres...\n")
                database.stop()
            }
            else -> println("\nPostgres wasn't stopped because it is not running\n")
        }
    }
}