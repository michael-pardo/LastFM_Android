package com.mistpaag.lastfm.trainee.views.main.topArtist

import android.content.Context
import android.util.DisplayMetrics
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mistpaag.lastfm.trainee.data.repository.Repository
import com.mistpaag.lastfm.trainee.models.TopArtist
import com.mistpaag.lastfm.trainee.utils.ScreenUtil
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.ArrayList

class TopArtistViewModel(private val repository:Repository, private val screenUtil: ScreenUtil) : ViewModel() {
    // TODO: Implement the ViewModel
    val topArtistList : LiveData<List<TopArtist>>
        get()= _topArtistList
    private val _topArtistList = MutableLiveData<List<TopArtist>>()

    fun fetchTopArtists(){
        val position = screenUtil.getPositionForScreenDensity()
        var artists = ArrayList<TopArtist>()
        viewModelScope.launch {
            repository.fetchArtists().collect {
                it.map { artist ->
                    var topArtist = artist.getTopArtis(position)
                    artists.add(topArtist)
                }
                _topArtistList.value = artists
            }
        }
    }
}