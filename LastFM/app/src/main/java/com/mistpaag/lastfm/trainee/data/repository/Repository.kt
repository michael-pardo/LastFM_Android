package com.mistpaag.lastfm.trainee.data.repository


import android.content.Context
import android.util.Log
import com.mistpaag.lastfm.trainee.data.local.dao.LastFMDao
import com.mistpaag.lastfm.trainee.data.remote.ApiService
import com.mistpaag.lastfm.trainee.models.database.TopTrack
import com.mistpaag.lastfm.trainee.models.database.TopArtist
import com.mistpaag.lastfm.trainee.utils.ScreenUtil
import com.mistpaag.lastfm.trainee.utils.haveConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class Repository(private val apiService: ApiService, private val localDB: LastFMDao, private val context: Context, private val screenUtil: ScreenUtil){

    var lastPageTopArtist = 1
    var lastPageTopTrack = 1

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
            fetchLocalArtists(lastPageTopTrack).collect {
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)

    private fun fetchLocalArtists(page:Int?=null)= flow{
        page?.let { lastPageTopArtist = page }
        val artists = localDB.fetchTopArtists(lastPageTopArtist)
        if (page == null) {
            if (artists.isNotEmpty()) lastPageTopArtist++
        }
        emit(artists)
    }.flowOn(Dispatchers.IO)

    suspend fun searchTopArtists(name:String)= flow{
        emit(localDB.searchTopArtists(name))
    }.flowOn(Dispatchers.IO)


    suspend fun fetchTracks() = flow<List<TopTrack>> {
        try {
            if (context.haveConnection()){
                val response = apiService.fetchTopTrack(lastPageTopTrack).await()
                var tracksList = ArrayList<TopTrack>()
                response.tracks.track.map {track ->
                    val position = screenUtil.getPositionForScreenDensity()
                    tracksList.add(track.getTopTrack(position, lastPageTopTrack))
                }
                localDB.insertTopTracks(tracksList)
                fetchLocalTracks().collect {
                    emit(it)
                }
            }else{
                fetchLocalTracks().collect {
                    emit(it)
                }
            }
        }catch (t:Throwable){
            fetchLocalTracks(lastPageTopTrack).collect {
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)

    private fun fetchLocalTracks(page:Int?=null)= flow{
        page?.let { lastPageTopTrack = page }
        val tracks = localDB.fetchTopTracks(lastPageTopTrack)
        if (page == null) {
            if (tracks.isNotEmpty()) lastPageTopTrack++
        }
        emit(tracks)
    }.flowOn(Dispatchers.IO)

    suspend fun searchTopTracks(name:String)= flow{
        emit(localDB.searchTopTracks(name))
    }.flowOn(Dispatchers.IO)

    suspend fun fetchTopTrack(name:String)= flow{
        emit(localDB.searchTopTrack(name))
    }.flowOn(Dispatchers.IO)

}








