package com.mrroboto.notimed.viewmodels

import androidx.lifecycle.ViewModel

import com.mrroboto.notimed.repositories.AppointmentRepository


class AppointmentViewModel(private val repository: AppointmentRepository): ViewModel() {
    val appointments = repository.appointments

}