package com.example.tmdb.di

import com.example.tmdb.details.MovieDetailsViewModel
import com.example.tmdb.home.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MoviesViewModel(get(), get()) }
    viewModel { MovieDetailsViewModel(get(), get()) }
}