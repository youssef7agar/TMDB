package com.example.tmdb.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdb.common.BaseViewModel
import com.example.tmdb.common.utils.whenFail
import com.example.tmdb.common.utils.whenSuccess
import com.example.tmdb.db.entities.MovieWithGenres
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val loadMovieUseCase: LoadMovieUseCase,
    private val getMovieUseCase: GetMovieUseCase
) :
    BaseViewModel<MovieViewState>() {
    override val _viewState = MutableLiveData<MovieViewState>().apply { MovieViewState() }

    fun loadMovie(id: Int) = launch {
        setState(MovieViewState()) { copy(loading = true) }
        loadMovieUseCase(id).whenSuccess { setState { MovieViewState() } }
            .whenFail { setState { MovieViewState(error = it) } }
    }

    fun getMovie(id: Int): LiveData<MovieWithGenres>{
        return getMovieUseCase(id)
    }

    private fun setState(
        block: MovieViewState.() -> MovieViewState
    ) {
        setState(MovieViewState(), block)
    }
}