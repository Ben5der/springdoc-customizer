package com.example.demo

import com.example.demo.model.Model
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@Tag(name = "uu")
class Controller() {

    @GetMapping()
    @Operation
    fun a(@ParameterObject pageable: Pageable): Page<Model>? =
        null
}