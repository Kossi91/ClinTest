package com.example.presentation.ui.fragments

import androidx.lifecycle.viewModelScope
import com.example.domain.either.Either
import com.example.domain.usecases.FetchFilmsUseCase
import com.example.presentation.base.BaseViewModel
import com.example.presentation.models.FilmsResponseUI
import com.example.presentation.models.toUI
import com.example.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val fetchFilmUseCase: FetchFilmsUseCase
) : BaseViewModel() {

    private val _filmState = MutableStateFlow<UIState<List<FilmsResponseUI>>>(UIState.Loading())
    val filmState get() = _filmState.asStateFlow()

    init {
        fetchFilms()
    }

    private fun fetchFilms() {
        viewModelScope.launch {
            fetchFilmUseCase("").collect {
                when (it) {
                    is Either.Left -> {
                        it.massage?.let { error ->
                            _filmState.value = UIState.Error(error)
                        }
                    }
                    is Either.Right -> {
                        it.data?.let {
                            _filmState.value = UIState.Success(it.map {film ->
                                film.toUI()
                            })
                        }
                    }
                }
            }
        }
    }
}