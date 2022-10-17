package com.example.demo.model

import io.swagger.v3.oas.annotations.media.Schema


data class MyPage<T>(val content: List<T>, val pagination: MyPagination) {
    data class MyPagination(val size: Int, @field:Schema(description = "636", example = "6363") val page: Int)
}