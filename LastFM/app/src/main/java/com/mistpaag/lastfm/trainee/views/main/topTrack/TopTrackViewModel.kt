package com.mistpaag.lastfm.trainee.views.main.topTrack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mistpaag.lastfm.trainee.data.repository.Repository
import com.mistpaag.lastfm.trainee.models.database.TopTrack
import com.mistpaag.lastfm.trainee.models.database.TopArtist
import com.mistpaag.lastfm.trainee.utils.ScreenUtil
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.ArrayList

class TopTrackViewModel(private val repository: Repository, private val screenUtil: ScreenUtil) : ViewModel() {

    val topTrackstList : LiveData<List<TopTrack>>
        get()= _topTrackstList
    private val _topTrackstList = MutableLiveData<List<TopTrack>>()

    val lastPage : LiveData<Int>
        get()= _lastPage
    private val _lastPage = MutableLiveData<Int>()

    val loadingNextPage : LiveData<Boolean>
        get()= _loadingNextPage
    private val _loadingNextPage = MutableLiveData<Boolean>(false)
    var trackList = ArrayList<TopTrack>()

    fun loadInitData(){
        repository.lastPageTopTrack = 1
    }

    fun fetchTopTracks() {
        val position = screenUtil.getPositionForScreenDensity()
        var artists = ArrayList<TopArtist>()
        viewModelScope.launch {
            repository.fetchTracks().collect { tracks->
                trackList.addAll(tracks)
                _topTrackstList.value = trackList
            }
        }
    }

    fun needOtherPage(){
        _loadingNextPage.value?.let {loadingPage ->
            if (!loadingPage){
                fetchTopTracks()
            }
        }
    }

    fun searchTopArtists(name: String) {
        viewModelScope.launch {
            repository.searchTopTracks(name).collect {
                _topTrackstList.value = it
            }
        }
    }

    fun setTopTracks() {
        _topTrackstList.value = trackList
    }


}