package com.example.tmdb.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tmdb.common.Constants
import com.example.tmdb.db.entities.Genre
import com.example.tmdb.db.entities.Movie
import com.example.tmdb.db.entities.MovieGenresCrossRef
import com.example.tmdb.db.entities.MovieWithGenres

@Dao
interface MoviesDao {
    suspend fun insertMovie(movieWithGenres: MovieWithGenres) {
        insertMovie(movieWithGenres.movie)
        insertGenre(movieWithGenres.genres)
        movieWithGenres.genres.map {
            MovieGenresCrossRef(
                movieWithGenres.movie.movieId,
                it.genreId
            )
        }.also(::insertCrossRef)
    }

    suspend fun insertMovie(movieWithGenres: List<MovieWithGenres>) {
        movieWithGenres.forEach { insertMovie(it) }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenre(genre: List<Genre>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCrossRef(ref: List<MovieGenresCrossRef>)


    @Transaction
    @Query("SELECT * FROM ${Constants.MOVIES_TABLE_NAME}")
    fun getMovies(): LiveData<List<MovieWithGenres>>

    @Query("SELECT * FROM ${Constants.MOVIES_TABLE_NAME} WHERE movieId=:id")
    fun getMovie(id: Int): LiveData<MovieWithGenres>

    // TODO getMovie(id: Int): LiveData<MovieWithGenres>
}