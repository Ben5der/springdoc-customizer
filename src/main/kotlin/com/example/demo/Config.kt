package com.example.demo

import com.example.demo.model.ErrorEntity
import io.swagger.v3.core.converter.ModelConverters
import io.swagger.v3.oas.models.OpenAPI
import org.springdoc.core.customizers.OpenApiCustomiser
import org.springdoc.core.providers.ObjectMapperProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct


@Configuration
class Config(private val springDocObjectMapper: ObjectMapperProvider) {

    @Bean
    fun openApiCustomiser(): OpenApiCustomiser {
        return OpenApiCustomiser { openApi: OpenAPI ->
            val mySchema = ModelConverters.getInstance().readAllAsResolvedSchema(ErrorEntity::class.java).schema
            openApi.components.schemas[mySchema.name] = mySchema
        }
    }

    @PostConstruct
    fun b() {
        ModelConverters.getInstance().addConverter(AModelConverter(springDocObjectMapper))
    }
}