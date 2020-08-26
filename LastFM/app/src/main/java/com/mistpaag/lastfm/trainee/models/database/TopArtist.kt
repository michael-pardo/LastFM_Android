package com.mistpaag.lastfm.trainee.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TopArtist(
    @PrimaryKey
    val name:String,
    val image:String,
    val url:String,
    val listeners:String,
    val page:Int
) {
    constructor():this("","","","",1)
}