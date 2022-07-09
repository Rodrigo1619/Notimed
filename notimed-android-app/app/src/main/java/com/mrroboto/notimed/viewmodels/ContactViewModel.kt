package com.mrroboto.notimed.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrroboto.notimed.data.models.Contact
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.network.responses.contact.OneContactResponse
import com.mrroboto.notimed.repositories.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactRepository) : ViewModel() {

    var currentName = MutableLiveData<String>()
    var currentNumber = MutableLiveData<String>()
    var currentAdress = MutableLiveData<String>()
    var currentSpecialization = MutableLiveData<String>()
    var currentStartHour = MutableLiveData("")
    var currentEndHour = MutableLiveData("")

    val apiResponse = MutableLiveData<ApiResponse<Any>>()
    val listResponse = MutableLiveData<ApiResponse<List<Contact>>>()
    val response = MutableLiveData<ApiResponse<OneContactResponse>>()

    fun createContact(
        name: String,
        phoneNumber: String,
        address: String,
        specialization: String,
        startHour: String,
        endHour: String,
    ) = viewModelScope.launch {
        //Una Couroutine o Corrutina es un patron de diseño para simplificar codigo
        // en android.

        // launch crea la corrutina, sigue el hilo de viewmodel, por lo que si el usuario
        // sale de la vista que usa este ViewModel se cancela el hilo

        apiResponse.value = ApiResponse.Loading(true)

        apiResponse.postValue(
            repository.addContact(
                name,
                phoneNumber,
                address,
                specialization,
                startHour,
                endHour
            )
        )
    }

    fun getContacts() {
        listResponse.value = ApiResponse.Loading(true)
        viewModelScope.launch(Dispatchers.IO) {
            listResponse.postValue(repository.getContacts())
        }
    }

    fun deleteContact(id: String) = viewModelScope.launch {
        apiResponse.value = ApiResponse.Loading(isLoading = true)

        apiResponse.postValue(repository.deleteContact(id))
    }


    fun updateContact(
        id: String,
        name: String,
        phoneNumber: String,
        address: String,
        specialization: String,
        startHour: String,
        endHour: String,
    ) {
        apiResponse.value = ApiResponse.Loading(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            apiResponse.postValue(
                repository.updateContact(
                    name,
                    phoneNumber,
                    address,
                    specialization,
                    startHour,
                    endHour,
                    id
                )
            )
        }
    }

    fun getOneContact(id: String) {
        response.value = ApiResponse.Loading(true)
        viewModelScope.launch(Dispatchers.Main){
            response.postValue(repository.getOneContact(id))
        }
    }
}