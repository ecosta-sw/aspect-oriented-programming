package com.creditas.aspectorientedprogramming.application.controllers.person

import com.creditas.aspectorientedprogramming.application.commons.resources.ResponseDataTO
import com.creditas.aspectorientedprogramming.configuration.IntegrationTests
import com.creditas.aspectorientedprogramming.configuration.readJsonResource
import com.fasterxml.jackson.module.kotlin.readValue
import java.nio.charset.StandardCharsets
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

internal class PersonControllerTest : IntegrationTests() {

    @Test
    fun `When pass valid data for create a new person, should HTTP STATUS CREATED`() {
        val jsonRequest = readJsonResource("person/request/create_person_with_success")
        val responseJson = readJsonResource("person/response/created_person_with_success")
        val expected = mapper.readValue<ResponseDataTO>(responseJson)

        mockMvc.perform(
            post("/person")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(jsonRequest)
        )
            .andExpect(status().isCreated)
            .andReturn().also { mvcResult ->
                val content = mvcResult.response.contentAsString

                val actual = mapper.readValue<ResponseDataTO>(content)

                assertThat(actual).usingRecursiveComparison(configuration).isEqualTo(expected)
            }
    }
}