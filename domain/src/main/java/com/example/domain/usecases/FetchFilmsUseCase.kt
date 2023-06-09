package com.example.domain.usecases

import com.example.domain.either.Either
import com.example.domain.models.FilmsResponse
import com.example.domain.repositories.FilmsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchFilmsUseCase @Inject constructor(
    private val repository: FilmsRepository
) {

    operator fun invoke(fields: String): Flow<Either<String, List<FilmsResponse>>> {
        return repository.fetchFilms(fields)
    }
}