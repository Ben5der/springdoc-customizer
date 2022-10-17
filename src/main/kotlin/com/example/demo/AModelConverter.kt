package com.example.demo

import com.example.demo.model.MyPage
import com.fasterxml.jackson.databind.JavaType
import io.swagger.v3.core.converter.AnnotatedType
import io.swagger.v3.core.converter.ModelConverter
import io.swagger.v3.core.converter.ModelConverterContext
import io.swagger.v3.oas.models.media.Schema
import org.springdoc.core.providers.ObjectMapperProvider
import org.springframework.core.ResolvableType
import org.springframework.data.domain.Page

class AModelConverter(private val springDocObjectMapper: ObjectMapperProvider) : ModelConverter {
    override fun resolve(
        annotatedType: AnnotatedType,
        context: ModelConverterContext,
        chain: Iterator<ModelConverter>
    ): Schema<*>? {
        val javaType: JavaType = springDocObjectMapper.jsonMapper().constructType(annotatedType.type)
        if (javaType.rawClass == Page::class.java) {
            val innerType = javaType.bindings.typeParameters.first().rawClass
            annotatedType.type = ResolvableType.forClassWithGenerics(
                MyPage::class.java,
                innerType
            ).type
        }
        return if (chain.hasNext()) chain.next().resolve(annotatedType, context, chain) else null
    }
}