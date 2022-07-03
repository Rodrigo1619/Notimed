package com.mrroboto.notimed.repositories

import androidx.lifecycle.MutableLiveData
import com.mrroboto.notimed.data.models.Reminder

class ReminderRepository {
    private var _reminders = listOf(
        Reminder("Acetaminofen", "4:30pm" ,"Tres veces al dia", "1", "Debe tomarse al ingerir alimentos"),
        Reminder("Loratadina", "5:30pm","Dos veces al dia", "2", "Debe tomarse al ingerir alimentos"),
        Reminder("Ibuprofeno", "8:00am","Una vez al dia", "1", "Debe tomarse al ingerir alimentos"),
        Reminder("Dolo-Nervilan", "11:00am","Dos veces al dia", "2", "Debe tomarse al ingerir alimentos")
    ).toMutableList()

    val reminders: MutableLiveData<List<Reminder>>
        get() = MutableLiveData(_reminders)

    fun addReminder(reminder: Reminder){
        _reminders.add(reminder)
        reminders.value = _reminders
    }
}