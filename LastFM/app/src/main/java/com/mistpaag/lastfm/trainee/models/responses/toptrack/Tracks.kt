package com.mistpaag.lastfm.trainee.models.responses.toptrack

import com.google.gson.annotations.SerializedName
import com.mistpaag.lastfm.trainee.models.responses.topartist.Attr

data class Tracks(
    @SerializedName("@attr")
    val attr: Attr,
    val track: List<Track>
)