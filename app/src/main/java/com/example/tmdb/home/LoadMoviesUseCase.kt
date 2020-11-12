package com.example.tmdb.home

import com.example.tmdb.common.Constants.Companion.PAGINATION_START
import com.example.tmdb.repository.MoviesRepo
import com.example.tmdb.common.utils.Result
import com.example.tmdb.common.utils.unwrapResult
import java.util.concurrent.atomic.AtomicInteger

class LoadMoviesUseCase(private val moviesRepo: MoviesRepo) {
    private val page = AtomicInteger(PAGINATION_START)

    suspend operator fun invoke(): Result<Unit> {
        page.set(PAGINATION_START)
        return moviesRepo.loadMovies(page.get()).unwrapResult({
            if (it) page.incrementAndGet()
            Result.Success(Unit)
        }, {
            Result.Error(it)
        })
    }

    suspend fun next(): Result<Unit> {
        if (page.get() == PAGINATION_START) return Result.Success(Unit)
        return moviesRepo.loadMovies(page.get()).unwrapResult({
            if (it) page.incrementAndGet()
            Result.Success(Unit)
        }, {
            Result.Error(it)
        })
    }
}