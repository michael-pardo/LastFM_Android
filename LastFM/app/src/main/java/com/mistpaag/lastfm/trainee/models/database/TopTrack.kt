package com.mistpaag.lastfm.trainee.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mistpaag.lastfm.trainee.models.responses.toptrack.ArtistTrack
import java.time.Duration
@Entity
data class TopTrack(
    @PrimaryKey
    val name:String,
    val duration: String,
    val url:String,
    val listeners:String,
    val image:String,
    @Embedded val artistTrack: ArtistTrack,
    val page:Int) {
}