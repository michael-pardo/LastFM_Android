package com.mistpaag.lastfm.trainee.models.responses.topartist

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class Topartists(
    @SerializedName("@attr")
    val attr: Attr,
    val artist: List<Artist>
)