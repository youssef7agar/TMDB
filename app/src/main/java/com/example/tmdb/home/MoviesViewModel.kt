package com.example.tmdb.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.tmdb.common.BaseViewModel
import com.example.tmdb.common.utils.whenFail
import com.example.tmdb.common.utils.whenSuccess
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val loadMoviesUseCase: LoadMoviesUseCase,
    private val getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel<MoviesListViewState>() {

    override val _viewState = MutableLiveData<MoviesListViewState>().apply { MoviesListViewState() }

    init {
        loadMoviesList()
    }

    fun loadMoviesList() = launch {
        setState { MoviesListViewState(loading = true) }
        loadMoviesUseCase().whenSuccess { setState { MoviesListViewState() } }
            .whenFail { setState { MoviesListViewState(error = it) } }
    }

    fun nextMoviesList() = launch {
        setState(MoviesListViewState()) { copy(loadingMore = true) }
        loadMoviesUseCase.next().whenSuccess { setState { MoviesListViewState() } }
            .whenFail { setState { MoviesListViewState(error = it) } }
    }

    fun movies() = getMoviesUseCase().map { it.map { it.movie } }

    private fun setState(
        block: MoviesListViewState.() -> MoviesListViewState
    ) {
        setState(MoviesListViewState(), block)
    }
}

