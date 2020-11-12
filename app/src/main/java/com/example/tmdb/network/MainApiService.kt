package com.example.tmdb.network

import com.example.tmdb.common.Constants
import com.example.tmdb.network.model.MovieListResponse
import com.example.tmdb.network.model.MovieListResponse.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApiService {

    @GET(Constants.MOVIES_LIST_URL)
    suspend fun getMoviesList(@Query("page") page: Int): MovieListResponse

    @GET(Constants.MOVIE_URL)
    suspend fun getMovie(@Path("movie_id") id: Int): MovieResponse
}