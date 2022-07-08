package com.mrroboto.notimed.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrroboto.notimed.data.models.Appointment
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.network.responses.appointment.OneAppointmentResponse

import com.mrroboto.notimed.repositories.AppointmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AppointmentViewModel(private val repository: AppointmentRepository): ViewModel() {
    var currentName = MutableLiveData<String>()
    var currentMedic = MutableLiveData<String>()
    var currentDate = MutableLiveData<String>()
    var currentHour = MutableLiveData<String>()
    var currentLocalization = MutableLiveData<String>()
    var currentConsiderations = MutableLiveData<String>()

    val apiResponse = MutableLiveData<ApiResponse<Any>>()
    val listResponse = MutableLiveData<ApiResponse<List<Appointment>>>()
    val response = MutableLiveData<ApiResponse<OneAppointmentResponse>>()

    fun createAppointment(
        isLoading: Boolean,
        appointmentName:String,
        doctorName:String,
        appointmentDate: String,
        appointmentHour: String,
        address: String,
        additionalNotes: String
    ) = viewModelScope.launch {
        apiResponse.value = ApiResponse.Loading(isLoading)

        apiResponse.postValue(
            repository.addAppointment(
                appointmentName,
                doctorName,
                appointmentDate,
                appointmentHour,
                address,
                additionalNotes
            )
        )
    }
    fun getAppointments(isLoading: Boolean) = viewModelScope.launch {
        listResponse.value = ApiResponse.Loading(isLoading)

        listResponse.postValue(repository.getAppointments())
    }
    fun deleteAppointments(id:String)=viewModelScope.launch {
        apiResponse.value = ApiResponse.Loading(isLoading = true)

        apiResponse.postValue(repository.deleteAppointment(id))
    }

    fun updateAppointment(
        id:String,
        name:String,
        medic: String,
        date: String,
        hour: String,
        localization: String,
        considerations: String
    ){
        apiResponse.value = ApiResponse.Loading(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            apiResponse.postValue(
                repository.updateAppointment(
                    name,
                    medic,
                    date,
                    hour,
                    localization,
                    considerations,
                    id
                )
            )
        }
    }

    fun getOneAppointment(id:String) = viewModelScope.launch {
        response.value = ApiResponse.Loading(true)

        response.postValue(repository.getOneAppointment(id))
    }

}