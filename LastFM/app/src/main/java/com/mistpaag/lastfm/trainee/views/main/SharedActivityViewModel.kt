package com.mistpaag.lastfm.trainee.views.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedActivityViewModel :ViewModel(){
    val searchText : LiveData<String>
        get() = _searchText
    private val _searchText = MutableLiveData<String>("")

    val isSearching : LiveData<Boolean>
        get() = _isSearching
    private val _isSearching = MutableLiveData(false)


    fun search(searchWord:String){
        _searchText.value = searchWord
    }

    fun isSearching(isSearch:Boolean){
        _isSearching.value = isSearch
    }



}