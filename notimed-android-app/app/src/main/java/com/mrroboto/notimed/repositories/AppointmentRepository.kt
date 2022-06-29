package com.mrroboto.notimed.repositories

import androidx.lifecycle.MutableLiveData
import com.mrroboto.notimed.data.models.Appointment

class AppointmentRepository {
    private var _appointments = listOf(
        Appointment("Gripe", "4:30pm" ,"10-07-2022", "1:00pm", "Antiguo Cuscatlan, Calle #5, Clinica MemoMed", "llevar sueter"),
        Appointment("Indigestion", "5:30pm","15-07-2022", "2:00pm", "Antiguo Cuscatlan, Calle #5, Clinica MemoMed", "no comer cosas solidas un dia antes"),
        Appointment("Dolor de cabeza", "8:00am","09-07-2022", "1:00pm", "Antiguo Cuscatlan, Calle #5, Clinica MemoMed","llevar el doloneurobion"),
        Appointment("Dolor de espalda", "11:00am","09-07-2022", "2:00pm", "Antiguo Cuscatlan, Calle #5, Clinica MemoMed", "")
    ).toMutableList()

    val appointments: MutableLiveData<List<Appointment>>
        get() = MutableLiveData(_appointments)

    fun addReminder(reminder: Appointment){
        _appointments.add(reminder)
        appointments.value = _appointments
    }
}