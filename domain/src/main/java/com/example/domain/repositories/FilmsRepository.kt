package com.example.domain.repositories

import com.example.domain.either.Either
import com.example.domain.models.FilmsResponse
import kotlinx.coroutines.flow.Flow


interface FilmsRepository {

    fun fetchFilms(fields: String): Flow<Either<String, List<FilmsResponse>>>
}