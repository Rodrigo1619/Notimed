package com.mrroboto.notimed.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrroboto.notimed.repositories.AppointmentRepository

class AppointmentViewModelFactory(private val appointmentRepository: AppointmentRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AppointmentViewModel::class.java)) {
            return AppointmentViewModel(appointmentRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}