package com.example.tmdb.home

import com.example.tmdb.repository.MoviesRepo

class GetMoviesUseCase(private val repo: MoviesRepo) {
    operator fun invoke() = repo.getMovies()
}