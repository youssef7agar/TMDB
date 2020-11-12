package com.example.tmdb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmdb.db.entities.Genre
import com.example.tmdb.db.entities.Movie
import com.example.tmdb.db.entities.MovieGenresCrossRef

@Database (entities = [Movie::class, Genre::class, MovieGenresCrossRef::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}