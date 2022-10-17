package com.example.demo

import com.example.demo.model.MyRs
import io.swagger.v3.core.converter.ModelConverters
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.media.Schema
import org.springdoc.core.customizers.OperationCustomizer
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.HandlerMethod

@Configuration
class ApiDocsOperationCustomizer : OperationCustomizer {
    override fun customize(operation: Operation, handlerMethod: HandlerMethod): Operation {
        operation
            .responses
            .values
            .map { it.content }
            .filter { it.containsKey("*/*") }
            .flatMap { it.values }
            .forEach { it.schema = customizeSchema(it.schema) }
        return operation
    }

    private fun customizeSchema(objSchema: Schema<*>): Schema<*> =
        ModelConverters
            .getInstance()
            .readAllAsResolvedSchema(MyRs::class.java)
            .schema
            .addProperty("payload", objSchema)
}