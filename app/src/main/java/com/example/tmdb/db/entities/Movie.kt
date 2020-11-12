package com.example.tmdb.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tmdb.common.Constants
import kotlinx.android.parcel.Parcelize

@Entity(tableName = Constants.MOVIES_TABLE_NAME)
@Parcelize
data class Movie(
    @PrimaryKey
    val movieId: Int,
    val title: String?,
    val voteAverage: Float?,
    val releaseDate: String?,
    val overview: String?,
    val isAdult: Boolean,
    val originalLanguage: String?,
    val posterPath: String?,
    val backdropPath: String?
) : Parcelable