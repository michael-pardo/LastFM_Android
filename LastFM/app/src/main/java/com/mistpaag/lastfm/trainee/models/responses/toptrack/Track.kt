package com.mistpaag.lastfm.trainee.models.responses.toptrack

import com.google.gson.annotations.SerializedName
import com.mistpaag.lastfm.trainee.models.database.TopTrack
import com.mistpaag.lastfm.trainee.models.responses.topartist.Image

data class Track(
    @SerializedName("@attr")
    val attr: AttrX,
    val artist: ArtistTrack,
    val duration: String,
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val streamable: Streamable,
    val url: String
){
    fun getTopTrack(positionWithScreen: Int, page:Int): TopTrack {
        return if (positionWithScreen >= image.size){
//            TopTrack(
//                name,
//                duration,
//                url,
//                listeners,
//                image[image.size - 1].url,
//                page
//            )
            TopTrack(
                name = name,
                duration = duration,
                image = image[image.size - 1].url,
                page = page,
                listeners = listeners,
                url = url,
                artistTrack = artist
            )
        }else{
            TopTrack(
                name = name,
                duration = duration,
                image = image[positionWithScreen].url,
                page = page,
                listeners = listeners,
                url = url,
                artistTrack = artist
            )
        }
    }
}