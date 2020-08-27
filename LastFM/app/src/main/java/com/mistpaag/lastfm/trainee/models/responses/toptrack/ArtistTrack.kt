package com.mistpaag.lastfm.trainee.models.responses.toptrack

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class ArtistTrack(
    val mbid: String,
    @ColumnInfo(name="artist")val name: String,
    @ColumnInfo(name="url_artist")val url: String
)