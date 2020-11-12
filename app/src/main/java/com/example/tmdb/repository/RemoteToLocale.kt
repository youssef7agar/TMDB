package com.example.tmdb.repository

import com.example.tmdb.db.entities.Genre
import com.example.tmdb.db.entities.Movie
import com.example.tmdb.db.entities.MovieWithGenres
import com.example.tmdb.network.model.MovieListResponse

fun remoteMovieToLocal(remoteMovie: MovieListResponse.MovieResponse): MovieWithGenres {
    return MovieWithGenres(
        Movie(
            movieId = remoteMovie.id,
            isAdult = remoteMovie.isAdult,
            backdropPath = remoteMovie.backdropPath,
            originalLanguage = remoteMovie.originalLanguage,
            overview = remoteMovie.overview,
            posterPath = remoteMovie.posterPath,
            releaseDate = remoteMovie.releaseDate,
            title = remoteMovie.title,
            voteAverage = remoteMovie.voteAverage
        ), genres = remoteMovie.genres?.map { Genre(it.id.toInt(), it.name) } ?: listOf()
    )
}