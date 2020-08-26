package com.mistpaag.lastfm.trainee.utils

import android.content.Context
import android.net.ConnectivityManager


object Const {

    val dbName = "last_fm.db"
    val url_prod = "http://ws.audioscrobbler.com/2.0/"

    const val URL_BASE = "http://ws.audioscrobbler.com"
    const val API_KEY = "829751643419a7128b7ada50de590067"
    const val PATH_VERSION = "/2.0/"

    const val VALUE_HYPED_ARTIST_METOD = "geo.gettopartists"
    const val PARAM_METOD = "method"
    const val PARAM_FORMAT = "format"
    const val PARAM_API_KEY = "api_key"
    const val PARAM_COUNTRY = "country"
    const val VALUE_COUNTRY = "spain"

    const val VALUE_JSON = "json"

    const val URL_HYPED_ARTIST =
          "$PATH_VERSION?" + PARAM_API_KEY + "=" + API_KEY + "&" + PARAM_METOD + "=" + VALUE_HYPED_ARTIST_METOD +
                "&" + PARAM_FORMAT + "=" + VALUE_JSON + "&" + PARAM_COUNTRY + "=" + VALUE_COUNTRY






}