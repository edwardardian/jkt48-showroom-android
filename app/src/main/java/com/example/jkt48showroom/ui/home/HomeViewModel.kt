package com.example.jkt48showroom.ui.home

import ApiService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jkt48showroom.data.model.Member
import com.example.jkt48showroom.data.remote.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private val _showroomLives = MutableLiveData<List<Member>>()
    val showroomLives: LiveData<List<Member>> = _showroomLives

    private val _recentLives = MutableLiveData<List<Member>>()
    val recentLives: LiveData<List<Member>> = _recentLives

    private val apiService: ApiService = ApiConfig.apiService

    fun fetchImages() {
        viewModelScope.launch {
            try {
                val members: List<Member>? = withContext(Dispatchers.IO) {
                    apiService.getAllMember().execute().body()
                }
                members?.let {
                    _showroomLives.postValue(it)

                    _recentLives.postValue(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    init {
        fetchImages()
    }
}
