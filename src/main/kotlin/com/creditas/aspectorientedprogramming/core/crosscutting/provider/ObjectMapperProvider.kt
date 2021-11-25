package com.creditas.aspectorientedprogramming.core.crosscutting.provider

import com.creditas.aspectorientedprogramming.infrastructure.configuration.jackson.LocalDateDeserializer
import com.creditas.aspectorientedprogramming.infrastructure.configuration.jackson.LocalDateSerializer
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies.LOWER_CAMEL_CASE
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.time.LocalDate

object ObjectMapperProvider {
    fun provider(): ObjectMapper {
        return jacksonObjectMapper().apply {
            propertyNamingStrategy = LOWER_CAMEL_CASE

            setDefaultPrettyPrinter(
                DefaultPrettyPrinter().apply {
                    indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                    indentObjectsWith(DefaultIndenter("  ", "\n"))
                }
            )

            setSerializationInclusion(JsonInclude.Include.NON_NULL)

            registerModule(JavaTimeModule())
            val module = SimpleModule()
            module.addSerializer(LocalDate::class.java, LocalDateSerializer())
            module.addDeserializer(LocalDate::class.java, LocalDateDeserializer())
            registerModule(module)
            
            configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            enable(SerializationFeature.INDENT_OUTPUT)
            enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
            enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
            enable(SerializationFeature.INDENT_OUTPUT)
            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            canSerialize(Boolean::class.java)

            deserializationConfig.withAppendedAnnotationIntrospector(JacksonAnnotationIntrospector())

            serializationConfig.withAppendedAnnotationIntrospector(JacksonAnnotationIntrospector())
        }
    }

    fun snakeCaseMapper(): ObjectMapper {
        return provider().apply {
            propertyNamingStrategy = SNAKE_CASE
        }
    }
}
