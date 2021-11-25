/*
...........................................................
Project .....................: commons-libraries
Creation Date ...............: 09/09/2021 17:26:41
Developer....................: eder
Copyright....................: 2021
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2021 - All Rights Reserved
...........................................................
*/
package com.creditas.aspectorientedprogramming.infrastructure.configuration.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateSerializer : StdSerializer<LocalDate>(LocalDate::class.java) {

    @Throws(IOException::class)
    override fun serialize(value: LocalDate, generator: JsonGenerator, provider: SerializerProvider) {
        generator.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE))
    }

    companion object {

        private const val serialVersionUID = -21L
    }
}