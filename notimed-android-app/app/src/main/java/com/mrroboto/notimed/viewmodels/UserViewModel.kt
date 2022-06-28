package com.mrroboto.notimed.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.network.responses.identity.LoginResponse
import com.mrroboto.notimed.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    var currentName = MutableLiveData<String>()
    var currentLastname = MutableLiveData<String>()
    var currentEmail = MutableLiveData<String>()
    var currentPassword = MutableLiveData<String>()
    var currentBirthday = MutableLiveData<String>()
    var currentGender = MutableLiveData<String>()

    val loginResponse = MutableLiveData<ApiResponse<Any>>()
    
    fun onLogin(email: String, password: String, context: Context) =  viewModelScope.launch{
       loginResponse.value = repository.login(email, password)
    }

    fun register(context: Context) {
        viewModelScope.launch {
            when (val response = repository.register(
                currentName.value.toString(),
                currentLastname.value.toString(),
                currentEmail.value.toString(),
                currentPassword.value.toString(),
                currentBirthday.value.toString(),
                currentGender.value.toString().lowercase()
            )) {
                is ApiResponse.Success -> response.data
                is ApiResponse.Failure -> {
                    when (response.errorCode) {
                        400 -> Toast.makeText(context, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                        200 -> {
                            Toast.makeText(context, "Usuario creado con exito!", Toast.LENGTH_SHORT)
                                .show()
                        }
                        else -> return@launch
                    }
                }
            }
        }
    }
}