package com.example.tmdb.network.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val results: ArrayList<MovieResponse>?
) {
    data class MovieResponse(
        val id: Int,
        val title: String?,
        @SerializedName("vote_average")
        val voteAverage: Float?,
        @SerializedName("release_date")
        val releaseDate: String?,
        val overview: String?,
        private val adult: Boolean?,
        @SerializedName("original_language")
        val originalLanguage: String?,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        val genres: List<Genre>?,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
    ) {
        val isAdult = adult == true
    }

    data class Genre(
        val id: String,
        val name: String
    )
}