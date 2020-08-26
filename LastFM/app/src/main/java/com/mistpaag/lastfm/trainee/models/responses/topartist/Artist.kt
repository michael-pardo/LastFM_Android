package com.mistpaag.lastfm.trainee.models.responses.topartist

import com.mistpaag.lastfm.trainee.models.database.TopArtist

data class Artist(
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
){
    fun getTopArtis(positionWithScreen: Int, page:Int): TopArtist {
        return if (positionWithScreen >= image.size){
            TopArtist(
                name,
                image[image.size - 1].url,
                url,
                listeners,
                page
            )
        }else{
            TopArtist(
                name,
                image[positionWithScreen].url,
                url,
                listeners,
                page
            )
        }

    }
}