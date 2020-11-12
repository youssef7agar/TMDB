package com.example.tmdb.di

import com.example.tmdb.common.Constants
import com.example.tmdb.network.MainApiService
import com.example.tmdb.network.createOkHttpClient
import com.example.tmdb.network.createService
import com.example.tmdb.network.moshi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single<Retrofit.Builder> {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
    }

    factory { createOkHttpClient() }
    single { moshi() }

    single {
        createService<MainApiService>(Constants.MAIN_BASE_URL, get())
    }
}