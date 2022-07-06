package com.mrroboto.notimed.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrroboto.notimed.data.models.Reminder
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.repositories.ReminderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReminderViewModel(private val repository: ReminderRepository) : ViewModel() {
    var currentName = MutableLiveData<String>()
    var currentEveryTimes = MutableLiveData<String>()
    var currentHour = MutableLiveData<String>()
    var currentDose = MutableLiveData<String>()
    var currentStartDay = MutableLiveData<String>()
    var currentEndDay = MutableLiveData<String>()
    var currentOption = MutableLiveData<String>()
    var currentCardId = MutableLiveData<String>()

    val apiResponse = MutableLiveData<ApiResponse<Any>>()
    val listResponse = MutableLiveData<ApiResponse<List<Reminder>>>()

    fun createReminder(
        isLoading: Boolean,
        name: String,
        startDate: String,
        endDate: String,
        dose: Float,
        option: Boolean,
        times: Int,
        hour: String
    ) = viewModelScope.launch {
        //Una Couroutine o Corrutina es un patron de diseño para simplificar codigo
        // en android.

        // launch crea la corrutina, sigue el hilo de viewmodel, por lo que si el usuario
        // sale de la vista que usa este ViewModel se cancela el hilo

        apiResponse.value = ApiResponse.Loading(isLoading)

        apiResponse.postValue(
            repository.addReminder(
                name,
                times,
                hour,
                dose,
                startDate,
                endDate,
                option
            )
        )
    }

    fun getReminders(isLoading: Boolean) = viewModelScope.launch {
        apiResponse.value = ApiResponse.Loading(isLoading)

        listResponse.postValue(repository.getReminders())
    }

    fun deleteReminder(id: String) = viewModelScope.launch {
        apiResponse.value = ApiResponse.Loading(isLoading = true)

        apiResponse.postValue(repository.deleteReminder(id))
    }

    fun updateReminder(
        id: String,
        name: String,
        startDate: String,
        endDate: String,
        dose: Float,
        option: Boolean,
        times: Int,
        hour: String
    ) {
        apiResponse.value = ApiResponse.Loading(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            apiResponse.postValue(
                repository.updateReminder(
                    name,
                    times,
                    hour,
                    dose,
                    startDate,
                    endDate,
                    option,
                    id
                )
            )
        }
    }
}