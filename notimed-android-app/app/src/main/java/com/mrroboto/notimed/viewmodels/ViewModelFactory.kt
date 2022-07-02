package com.mrroboto.notimed.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrroboto.notimed.repositories.AppointmentRepository
import com.mrroboto.notimed.repositories.ContactRepository
import com.mrroboto.notimed.repositories.ReminderRepository
import com.mrroboto.notimed.repositories.UserRepository

class ViewModelFactory(
    private val repository: Any
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(repository as UserRepository) as T
        }
        if (modelClass.isAssignableFrom(AppointmentViewModel::class.java)) {
            return AppointmentViewModel(repository as AppointmentRepository) as T
        }
        if(modelClass.isAssignableFrom(ReminderViewModel::class.java)) {
            return ReminderViewModel(repository as ReminderRepository) as T
        }
        if(modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            return ContactViewModel(repository as ContactRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}