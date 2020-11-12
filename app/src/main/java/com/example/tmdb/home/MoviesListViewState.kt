package com.example.tmdb.home

import java.lang.Exception

data class MoviesListViewState(
    val loading: Boolean = false,
    val loadingMore: Boolean = false,
    val error: Exception? = null
)