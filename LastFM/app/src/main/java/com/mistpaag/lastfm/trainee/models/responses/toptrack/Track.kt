package com.mistpaag.lastfm.trainee.models.responses.toptrack

data class Track(
    val @attr: AttrX,
    val artist: Artist,
    val duration: String,
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val streamable: Streamable,
    val url: String
)