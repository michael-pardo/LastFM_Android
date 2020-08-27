package com.mistpaag.lastfm.trainee.views.detail.topTrack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mistpaag.lastfm.trainee.data.repository.Repository
import com.mistpaag.lastfm.trainee.models.database.TopTrack
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailTopTrackViewModel(private val repository: Repository) : ViewModel() {

    val topTrack : LiveData<TopTrack>
        get()= _topTrack
    private val _topTrack = MutableLiveData<TopTrack>()

    fun fetchTopTrack(name:String){
        viewModelScope.launch {
            repository.fetchTopTrack(name).collect {
                _topTrack.value = it
            }
        }
    }
}