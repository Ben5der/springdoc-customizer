package com.example.demo.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema
data class MyRs<T>(val payload: T, val errors: List<ErrorEntity>)
