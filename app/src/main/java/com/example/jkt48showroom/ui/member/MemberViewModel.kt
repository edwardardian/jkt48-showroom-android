package com.example.jkt48showroom.ui.member

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jkt48showroom.data.model.Member
import com.example.jkt48showroom.data.remote.ApiConfig.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemberViewModel : ViewModel() {
    private val _membersImage = MutableLiveData<List<String>>()
    val membersImage: LiveData<List<String>> = _membersImage

    private val _membersName = MutableLiveData<List<String>>()
    val membersName: LiveData<List<String>> = _membersName

    fun fetchMemberData() {
        viewModelScope.launch {
            try {
                val memberData: List<Member>? = withContext(Dispatchers.IO) {
                    val allMember = apiService.getAllMember().execute().body() ?: emptyList()
                    val allTrainee = apiService.getAllTrainee().execute().body() ?: emptyList()
                    allMember + allTrainee
                }
                memberData?.let {
                    val imagesUrl = it.map { member -> member.image }
                    val namesUrl = it.map { member -> member.name }
                    _membersImage.postValue(imagesUrl)
                    _membersName.postValue(namesUrl)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
