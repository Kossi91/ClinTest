package com.example.data.remote.dtos

import com.example.domain.models.FilmsResponse
import com.google.gson.annotations.SerializedName

data class FilmsResponseDto(
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("title")
    val title: String = "",
)

fun FilmsResponseDto.toDomain(): FilmsResponse {
    return FilmsResponse(
        originalTitle,
        title
    )
}