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
                val response = withContext(Dispatchers.IO) {
                    ApiConfig.apiService.getMember(mainName).execute()
                }
                if (response.isSuccessful) {
                    val member = response.body()
                    _filteredMember.postValue(member)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
