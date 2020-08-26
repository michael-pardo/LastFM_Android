package com.mistpaag.lastfm.trainee.data.repository


import com.mistpaag.lastfm.trainee.data.remote.ApiService
import com.mistpaag.lastfm.trainee.models.TopArtist
import com.mistpaag.lastfm.trainee.models.responses.topartist.Artist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


class Repository(private val apiService: ApiService){



    suspend fun fetchArtists() = flow<List<Artist>> {
        try {
            val call = apiService.fetchTopArtist().await()
            emit(call.topartists.artist)
        }catch (t:Throwable){
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun fetchTracks() = flow<List<Artist>> {
        try {
            val call = apiService.fetchTopArtist().await()
            emit(call.topartists.artist)
        }catch (t:Throwable){
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)

}








