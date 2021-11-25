package com.creditas.aspectorientedprogramming.application.controllers.company

import com.creditas.aspectorientedprogramming.application.commons.resources.ResponseDataTO
import com.creditas.aspectorientedprogramming.application.commons.resources.ResponseErrorTO
import com.creditas.aspectorientedprogramming.configuration.IntegrationTests
import com.creditas.aspectorientedprogramming.configuration.readJsonResource
import com.fasterxml.jackson.module.kotlin.readValue
import java.nio.charset.StandardCharsets
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

internal class CompanyControllerTest : IntegrationTests() {

    @Test
    fun `When pass valid data for create a new person, should HTTP STATUS CREATED`() {
        val jsonRequest = readJsonResource("company/request/create_company_with_success")
        val responseJson = readJsonResource("company/response/created_company_with_success")
        val expected = mapper.readValue<ResponseDataTO>(responseJson)

        mockMvc.perform(
            post("/company")
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

    @Test
    fun `When pass invalid data for create a new person, should HTTP STATUS UNPROCESSABLE_ENTITY`() {
        val jsonRequest = readJsonResource("company/request/create_company_with_empty_data")
        val responseJson = readJsonResource("company/response/create_company_with_empty_data_error")
        val expected = mapper.readValue<ResponseErrorTO>(responseJson)

        mockMvc.perform(
            post("/company")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(jsonRequest)
        )
            .andExpect(status().isUnprocessableEntity)
            .andReturn().also { mvcResult ->
                val content = mvcResult.response.contentAsString
                val actual = mapper.readValue<ResponseErrorTO>(content)

                assertThat(actual).usingRecursiveComparison(configuration).isEqualTo(expected)
            }
    }
}