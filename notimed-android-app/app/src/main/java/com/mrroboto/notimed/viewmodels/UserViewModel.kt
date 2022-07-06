package com.mrroboto.notimed.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.repositories.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    var currentName = MutableLiveData<String>()
    var currentLastname = MutableLiveData<String>()
    var currentEmail = MutableLiveData<String>()
    var currentPassword = MutableLiveData<String>()
    var currentBirthday = MutableLiveData<String>()
    var currentGender = MutableLiveData<String>()

    val apiResponse = MutableLiveData<ApiResponse<Any>>()
    val get = MutableLiveData<ApiResponse<Any>>()

    fun onLogin(email: String, password: String, isLoading: Any) = viewModelScope.launch {
        apiResponse.value = ApiResponse.Loading(isLoading)
        apiResponse.postValue(
            repository.login(email, password)
        )
    }

    fun register(isLoading: Any) = viewModelScope.launch {
        apiResponse.value = ApiResponse.Loading(isLoading)

        apiResponse.postValue(repository.register(
            currentName.value.toString(),
            currentLastname.value.toString(),
            currentEmail.value.toString(),
            currentPassword.value.toString(),
            currentBirthday.value.toString(),
            currentGender.value.toString().lowercase()
        ))


    }

    fun whoami(isLoading: Any) = viewModelScope.launch {
        apiResponse.value = ApiResponse.Loading(isLoading)
        apiResponse.postValue(repository.whoami())
    }

    fun recoverpassword(isLoading: Any) = viewModelScope.launch {
        apiResponse.value = ApiResponse.Loading(isLoading)
        apiResponse.postValue(repository.recoverPassword(currentEmail.value.toString()))
    }

    fun getId() = viewModelScope.launch {
        get.value = ApiResponse.Loading(isLoading = true)

        get.postValue(repository.getInfoUser())
    }
}