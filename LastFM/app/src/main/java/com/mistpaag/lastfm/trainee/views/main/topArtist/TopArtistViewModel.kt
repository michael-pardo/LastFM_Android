package com.mistpaag.lastfm.trainee.views.main.topArtist

import android.util.Log
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

class TopArtistViewModel(private val repository:Repository) : ViewModel() {
    // TODO: Implement the ViewModel
    val topArtistList : LiveData<List<TopArtist>>
        get()= _topArtistList
    private val _topArtistList = MutableLiveData<List<TopArtist>>()

    val lastPage : LiveData<Int>
        get()= _lastPage
    private val _lastPage = MutableLiveData<Int>()

    val loadingNextPage : LiveData<Boolean>
        get()= _loadingNextPage
    private val _loadingNextPage = MutableLiveData<Boolean>(false)
    var artists = ArrayList<TopArtist>()

    fun loadInitData(){
        repository.lastPageTopArtist = 1
    }

    fun fetchTopArtists(){
        if (!_loadingNextPage.value!!){
            viewModelScope.launch {
                _loadingNextPage.value = true
                repository.fetchArtists().collect {
                    artists.addAll(it)
                    _topArtistList.value = artists
                    _loadingNextPage.value = false
                }
            }
        }

    }


    fun needOtherPage(){
        _loadingNextPage.value?.let {loadingPage ->
            if (!loadingPage){
                fetchTopArtists()
            }
        }
    }

    fun searchTopArtists(name: String) {
        viewModelScope.launch {
            repository.searchTopArtists(name).collect {
                _topArtistList.value = it
            }
        }
    }

    fun setTopArtists() {
        _topArtistList.value = artists
    }
}