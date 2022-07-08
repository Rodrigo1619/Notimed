package com.mrroboto.notimed.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrroboto.notimed.data.models.Contact
import com.mrroboto.notimed.data.models.Reminder
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.network.responses.contact.Days
import com.mrroboto.notimed.network.responses.contact.OneContactResponse
import com.mrroboto.notimed.network.responses.reminder.OneReminderResponse
import com.mrroboto.notimed.repositories.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactRepository ):ViewModel(){

    var currentName = MutableLiveData<String>()
    var currentNumber = MutableLiveData<String>()
    var currentAdress = MutableLiveData<String>()
    var currentSpecialization = MutableLiveData<String>()
    var currentStartHour = MutableLiveData("")
    var currentEndHour = MutableLiveData("")
    var currentDays = MutableLiveData<String>()

    val apiResponse = MutableLiveData<ApiResponse<Any>>()
    val listResponse = MutableLiveData<ApiResponse<List<Contact>>>()
    val response = MutableLiveData<ApiResponse<OneContactResponse>>()

    fun createContact(
        isLoading: Boolean,
        name: String,
        phoneNumber: String,
        address: String,
        specialization: String,
        startHour: String,
        endHour: String,
        days: Days
    ) = viewModelScope.launch {
        //Una Couroutine o Corrutina es un patron de diseño para simplificar codigo
        // en android.

        // launch crea la corrutina, sigue el hilo de viewmodel, por lo que si el usuario
        // sale de la vista que usa este ViewModel se cancela el hilo

        apiResponse.value = ApiResponse.Loading(isLoading)

        apiResponse.postValue(
            repository.addContact(
                name,
                phoneNumber,
                address,
                specialization,
                startHour,
                endHour,
                days
            )
        )
    }

    fun getContacts(isLoading: Boolean) = viewModelScope.launch {
        apiResponse.value = ApiResponse.Loading(isLoading)

        listResponse.postValue(repository.getContacts())
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
        days: Days
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
                    days,
                    id
                )
            )
        }
    }

    fun getOneContact(id: String) = viewModelScope.launch {
        apiResponse.value = ApiResponse.Loading(isLoading = true)

        response.postValue(repository.getOneContact(id))
    }
}