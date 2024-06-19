package com.example.jkt48showroom.ui.member_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jkt48showroom.data.model.Member
import com.example.jkt48showroom.data.remote.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemberDetailViewModel : ViewModel() {
    private val _filteredMember = MutableLiveData<Member?>()
    val filteredMember: LiveData<Member?> = _filteredMember

    fun fetchMemberData(mainName: String) {
        viewModelScope.launch {
            try {
                val members: List<Member>? = withContext(Dispatchers.IO) {
                    val allMember = ApiConfig.apiService.getAllMember().execute().body() ?: emptyList()
                    val allTrainee = ApiConfig.apiService.getAllTrainee().execute().body() ?: emptyList()
                    allMember + allTrainee
                }
                val member = members?.find { it.main_name == mainName }
                _filteredMember.postValue(member)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
