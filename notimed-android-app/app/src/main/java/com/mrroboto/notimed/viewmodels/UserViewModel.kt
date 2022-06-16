package com.mrroboto.notimed.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository): ViewModel(){
    var currentName = MutableLiveData<String>()
    var currentLastname = MutableLiveData<String>()
    var currentEmail = MutableLiveData<String>()
    var currentPassword = MutableLiveData<String>()
    var currentBirthday = MutableLiveData<String>()
    var currentGender = MutableLiveData<String>()

    fun onLogin(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.login(
                email, password)) {
                is ApiResponse.Success -> response.data
                is ApiResponse.Failure -> response.errorBody
            }
        }
    }
}