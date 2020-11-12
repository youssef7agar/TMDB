package com.example.tmdb.di

val appComponent = listOf(
    networkModule,
    repoModule,
    useCasesModule,
    viewModelModule,
    databaseModule
)