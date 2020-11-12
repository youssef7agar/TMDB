package com.example.tmdb.db.entities

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "genreId"])
class MovieGenresCrossRef(
    val movieId: Int,
    val genreId: Int
)