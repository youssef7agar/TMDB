package com.example.tmdb.repository

import androidx.lifecycle.LiveData
import com.example.tmdb.common.utils.Result
import com.example.tmdb.common.utils.tryCall
import com.example.tmdb.db.MoviesDao
import com.example.tmdb.db.entities.MovieWithGenres
import com.example.tmdb.network.MainApiService
class MoviesRepo(
    private val mainApiService: MainApiService,
    private val moviesDao: MoviesDao
) {

    suspend fun loadMovies(page: Int = 0): Result<Boolean> {
        return tryCall {
            val movieResponse = mainApiService.getMoviesList(page)
            val movies = movieResponse.results?.map { remoteMovieToLocal(it) }
            if (movies.isNullOrEmpty()) {
                Result.Success(false)
            } else {
                moviesDao.insertMovie(movies)
                Result.Success(true)
            }
        }
    }

    fun getMovies(): LiveData<List<MovieWithGenres>> {
        return moviesDao.getMovies()
    }

    suspend fun loadMovie(id: Int): Result<MovieWithGenres> {
        return tryCall {
            val movie = mainApiService.getMovie(id)
            moviesDao.insertMovie(remoteMovieToLocal(movie))
            Result.Success(remoteMovieToLocal(movie))
        }
    }

    fun getMovie(id: Int): LiveData<MovieWithGenres>{
        return moviesDao.getMovie(id)
    }
}
