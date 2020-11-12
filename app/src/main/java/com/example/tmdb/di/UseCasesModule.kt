package com.example.tmdb.di

import com.example.tmdb.details.GetMovieUseCase
import com.example.tmdb.details.LoadMovieUseCase
import com.example.tmdb.home.GetMoviesUseCase
import com.example.tmdb.home.LoadMoviesUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory { LoadMoviesUseCase(get()) }
    factory { LoadMovieUseCase(get()) }
    factory { GetMoviesUseCase(get()) }
    factory { GetMovieUseCase(get()) }
}