package com.mrroboto.notimed.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.network.dto.LoginResponse
import com.mrroboto.notimed.repositories.UserRepository
import kotlinx.coroutines.*

class UserViewModel(private val repository: UserRepository): ViewModel(){
    var currentName = MutableLiveData<String>()
    var currentLastname = MutableLiveData<String>()
    var currentEmail = MutableLiveData<String>()
    var currentPassword = MutableLiveData<String>()
    var currentBirthday = MutableLiveData<String>()
    var currentGender = MutableLiveData<String>()

    var status = MutableLiveData<Int>()


    fun onLogin(email: String, password: String, context: Context) {
        viewModelScope.launch(Dispatchers.Main) {

            when (val response = repository.login(email, password)){
                is ApiResponse.Success -> {
                    response.data
                    status.postValue(200)
                }
                is ApiResponse.Failure -> {
                    status.postValue(response.errorCode)
                    TODO("Validaciones de errores (se quedan en null o se repiten")
                }
            }
        }
    }
}