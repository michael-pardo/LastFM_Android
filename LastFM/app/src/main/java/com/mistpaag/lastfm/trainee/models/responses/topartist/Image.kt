package com.mistpaag.lastfm.trainee.models.responses.topartist

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val url: String,
    val size: String
)