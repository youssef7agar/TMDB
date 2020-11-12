package com.example.tmdb.db

import android.app.Application
import androidx.room.Room

fun createDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "movies")
        .build()
}

fun createMovieDao(database: AppDatabase): MoviesDao {
    return database.moviesDao()
}