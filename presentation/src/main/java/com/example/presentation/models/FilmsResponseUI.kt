package com.example.presentation.models

import com.example.domain.models.FilmsResponse

data class FilmsResponseUI(
    val originalTitle: String = "",
    val title: String = "",
)

fun FilmsResponse.toUI(): FilmsResponseUI{
    return FilmsResponseUI(
    title,
    originalTitle
    )
}