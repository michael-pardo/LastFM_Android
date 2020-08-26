package com.mistpaag.lastfm.trainee.models

data class TopArtist(val name:String, val image:String, val url:String, val listeners:String) {
    constructor():this("","","","")
}