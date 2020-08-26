package com.mistpaag.lastfm.trainee.data.remote

import com.mistpaag.lastfm.trainee.models.responses.topartist.TopArtistFM
import com.mistpaag.lastfm.trainee.utils.Const
import kotlinx.coroutines.Deferred
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
//    @FormUrlEncoded
    @GET(Const.URL_HYPED_ARTIST)
    fun fetchTopArtist(
//        @Query("method") method: String,
//        @Query("country") country: String,
//        @Query("api_key") apiKey: String,
//        @Query("format") format: String
    ): Deferred<TopArtistFM>
}