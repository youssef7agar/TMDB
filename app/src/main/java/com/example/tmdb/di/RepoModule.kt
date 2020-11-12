package com.example.tmdb.di

import com.example.tmdb.repository.MoviesRepo
import org.koin.dsl.module

val repoModule = module {

    factory { MoviesRepo(get(), get()) }
}