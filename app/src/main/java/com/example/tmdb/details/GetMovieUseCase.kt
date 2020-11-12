package com.example.tmdb.details

import com.example.tmdb.repository.MoviesRepo

class GetMovieUseCase(private val moviesRepo: MoviesRepo) {
    operator fun invoke(id: Int) = moviesRepo.getMovie(id)
}