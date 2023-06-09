package com.example.data.repositories

import com.example.data.base.BaseRepository
import com.example.data.remote.apiservices.FilmsApiService
import com.example.data.remote.dtos.toDomain
import com.example.domain.either.Either
import com.example.domain.models.FilmsResponse
import com.example.domain.repositories.FilmsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(private val apiService: FilmsApiService) :
    BaseRepository(),
    FilmsRepository {

    override fun fetchFilms(fields: String): Flow<Either<String, List<FilmsResponse>>> = doRequest {
        apiService.fetchFilms(fields).map {
            it.toDomain()
        }
    }
}