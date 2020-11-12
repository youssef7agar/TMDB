package com.example.tmdb.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "genre")
data class Genre(
    @PrimaryKey
    val genreId: Int,
    val name: String
) : Parcelable