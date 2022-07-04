package com.mrroboto.notimed.viewmodels

import androidx.lifecycle.ViewModel
import com.mrroboto.notimed.repositories.ReminderRepository

class ReminderViewModel(private val repository: ReminderRepository):ViewModel() {
    val reminders = repository.reminders

}