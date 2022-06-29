package com.mrroboto.notimed.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrroboto.notimed.repositories.ReminderRepository

class ReminderViewModelFactory(private val reminderRepository: ReminderRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ReminderViewModel::class.java)) {
            return ReminderViewModel(reminderRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}