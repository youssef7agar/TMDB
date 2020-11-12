package com.example.tmdb.common

class Constants {
    companion object {
        const val MAIN_BASE_URL = "https://api.themoviedb.org/3/"

        const val MOVIES_LIST_URL = "trending/movie/week?api_key=d3cce19597af3de623fad70068d7a120"
        const val MOVIE_URL = "movie/{movie_id}?api_key=d3cce19597af3de623fad70068d7a120&language=en-US"

        const val MAIN_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

        const val PAGINATION_START = 1

        const val MOVIES_TABLE_NAME = "movies"
    }
}