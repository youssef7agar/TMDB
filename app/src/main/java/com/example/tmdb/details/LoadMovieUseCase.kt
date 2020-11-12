package com.example.tmdb.details

import com.example.tmdb.common.utils.Result
import com.example.tmdb.repository.MoviesRepo
import com.example.tmdb.common.utils.unwrapResult

class LoadMovieUseCase(private val moviesRepo: MoviesRepo) {
    suspend operator fun invoke(id: Int): Result<Unit> {
        return moviesRepo.loadMovie(id).unwrapResult({
            Result.Success(Unit)
        },{
            Result.Error(it)
        })
    }
}