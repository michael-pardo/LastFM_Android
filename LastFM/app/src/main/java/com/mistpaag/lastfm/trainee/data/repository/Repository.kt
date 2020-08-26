package com.mistpaag.lastfm.trainee.data.repository


import android.content.Context
import android.util.Log
import com.mistpaag.lastfm.trainee.data.local.dao.LastFMDao
import com.mistpaag.lastfm.trainee.data.remote.ApiService
import com.mistpaag.lastfm.trainee.models.database.TopArtist
import com.mistpaag.lastfm.trainee.models.responses.topartist.Artist
import com.mistpaag.lastfm.trainee.utils.Const
import com.mistpaag.lastfm.trainee.utils.ScreenUtil
import com.mistpaag.lastfm.trainee.utils.haveConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class Repository(private val apiService: ApiService, private val localDB: LastFMDao, private val context: Context, private val screenUtil: ScreenUtil){

    var lastPageTopArtist = 1

    suspend fun fetchArtists() = flow<List<TopArtist>> {
        try {
            if (context.haveConnection()){
                val response = apiService.fetchTopArtist(lastPageTopArtist).await()
                var artistsList = ArrayList<TopArtist>()
                response.topartists.artist.map {artist ->
                    val position = screenUtil.getPositionForScreenDensity()
                    artistsList.add(artist.getTopArtis(position, lastPageTopArtist))
                }
                localDB.insertTopArtists(artistsList)
                fetchLocalArtists().collect {
                    emit(it)
                }
            }else{
                fetchLocalArtists().collect {
                    emit(it)
                }
            }
        }catch (t:Throwable){
            Log.d("lol", " error")
            fetchLocalArtists().collect {
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)

    private fun fetchLocalArtists()= flow{
        val artists = localDB.fetchTopArtists(lastPageTopArtist)
        if (artists.isNotEmpty()) lastPageTopArtist++
        Log.d("lol data", artists.size.toString())
        emit(artists)
    }.flowOn(Dispatchers.IO)

    suspend fun searchTopArtists(name:String)= flow{
        emit(localDB.searchTopArtists(name))
    }.flowOn(Dispatchers.IO)


    suspend fun fetchTracks() = flow<List<Artist>> {
        try {
            val call = apiService.fetchTopArtist(2).await()
            emit(call.topartists.artist)
        }catch (t:Throwable){
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)

}








