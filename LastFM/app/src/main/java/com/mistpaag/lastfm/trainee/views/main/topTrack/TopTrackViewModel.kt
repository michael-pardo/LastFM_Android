package com.mistpaag.lastfm.trainee.views.main.topTrack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mistpaag.lastfm.trainee.data.repository.Repository
import com.mistpaag.lastfm.trainee.models.database.TopArtist
import com.mistpaag.lastfm.trainee.utils.ScreenUtil
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.ArrayList

class TopTrackViewModel(private val repository: Repository, private val screenUtil: ScreenUtil) : ViewModel() {

    val topArtistList : LiveData<List<TopArtist>>
        get()= _topArtistList
    private val _topArtistList = MutableLiveData<List<TopArtist>>()

    fun fetchTopTracks() {
        val position = screenUtil.getPositionForScreenDensity()
        var artists = ArrayList<TopArtist>()
        viewModelScope.launch {
            repository.fetchTracks().collect {
                it.map { artist ->
                    var topArtist = artist.getTopArtis(position,2)
                    artists.add(topArtist)
                }
                _topArtistList.value = artists
            }
        }
    }


}