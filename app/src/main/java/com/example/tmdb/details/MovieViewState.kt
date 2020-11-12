package com.example.tmdb.details

import java.lang.Exception

data class MovieViewState(
    val loading: Boolean = false,
    val error: Exception? = null
)