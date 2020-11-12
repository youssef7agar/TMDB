package com.example.tmdb.db.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.tmdb.db.entities.Genre
import com.example.tmdb.db.entities.Movie
import com.example.tmdb.db.entities.MovieGenresCrossRef

data class MovieWithGenres(
    @Embedded
    val movie: Movie,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(MovieGenresCrossRef::class)
    )
    val genres: List<Genre>
)