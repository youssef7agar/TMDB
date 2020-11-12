package com.example.tmdb.di

import com.example.tmdb.db.createDatabase
import com.example.tmdb.db.createMovieDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { createDatabase(androidApplication()) }
    single { createMovieDao(get()) }
}