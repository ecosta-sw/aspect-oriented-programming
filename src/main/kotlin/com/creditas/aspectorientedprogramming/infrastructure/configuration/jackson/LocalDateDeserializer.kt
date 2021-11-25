/*
...........................................................
Project .....................: commons-libraries
Creation Date ...............: 09/09/2021 17:26:03
Developer....................: eder
Copyright....................: 2021
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2021 - All Rights Reserved
...........................................................
*/
package com.creditas.aspectorientedprogramming.infrastructure.configuration.jackson

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.io.IOException
import java.time.LocalDate

class LocalDateDeserializer : StdDeserializer<LocalDate>(LocalDate::class.java) {

    @Throws(IOException::class)
    override fun deserialize(jsonParser: JsonParser, context: DeserializationContext): LocalDate {
        return LocalDate.parse(jsonParser.readValueAs(String::class.java))
    }

    companion object {

        private const val serialVersionUID = -12232L
    }
}