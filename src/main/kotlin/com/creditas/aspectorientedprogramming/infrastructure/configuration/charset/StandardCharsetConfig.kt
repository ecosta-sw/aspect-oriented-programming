package com.creditas.aspectorientedprogramming.infrastructure.configuration.charset

import java.nio.charset.StandardCharsets
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class StandardCharsetConfig : WebMvcConfigurer {
    override fun configureMessageConverters(converters: List<HttpMessageConverter<*>>) {
        converters.stream()
            .filter { converter: HttpMessageConverter<*>? -> converter is MappingJackson2HttpMessageConverter }
            .findFirst()
            .ifPresent { converter: HttpMessageConverter<*> ->
                (converter as MappingJackson2HttpMessageConverter).defaultCharset = StandardCharsets.UTF_8
            }
    }
}