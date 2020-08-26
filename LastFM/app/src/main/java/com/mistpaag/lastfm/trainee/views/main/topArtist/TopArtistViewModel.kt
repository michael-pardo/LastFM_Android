package com.mistpaag.lastfm.trainee.views.main.topArtist

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mistpaag.lastfm.trainee.data.repository.Repository
import com.mistpaag.lastfm.trainee.models.TopArtist
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.ArrayList

class TopArtistViewModel(val repository:Repository, val context: Context) : ViewModel() {
    // TODO: Implement the ViewModel
    val topArtistList : LiveData<List<TopArtist>>
        get()= _topArtistList
    private val _topArtistList = MutableLiveData<List<TopArtist>>()

    fun fetchTopArtists(){
        val position = getPositionForScreenDensity()
        var artists = ArrayList<TopArtist>()
        viewModelScope.launch {
            repository.fetchArtist().collect {
                it.map { artist ->
                    var topArtist = artist.getTopArtis(position)
                    artists.add(topArtist)
                }
                Log.d("lol", "Trajo data")
                _topArtistList.value = artists
                Log.d("lol", "Trajo data ${artists.size}")
            }
        }
    }

    private fun getPositionForScreenDensity():Int {
        val metrics: DisplayMetrics = context.resources.displayMetrics
        return when (metrics.density) {
            in 0f..1f -> 0
            in 1f..1.5f -> 1
            in 1.5f..2f -> 2
            in 2f..3f -> 3
            else -> 4
        }
    }
}