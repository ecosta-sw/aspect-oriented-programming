package com.creditas.aspectorientedprogramming.infrastructure.configuration

import com.creditas.aspectorientedprogramming.core.crosscutting.provider.ObjectMapperProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationBeans {

    @Bean
    fun objectMapper() = ObjectMapperProvider.snakeCaseMapper()
}