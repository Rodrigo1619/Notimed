package com.mrroboto.notimed.network.responses.appointment

import com.google.gson.annotations.SerializedName
import com.mrroboto.notimed.data.models.Appointment

data class AppointmentRequest(
    @SerializedName("name") val name: String,
    @SerializedName("medic") val medic: String,
    @SerializedName("date") val date: String,
    @SerializedName("hour") val hour: String,
    @SerializedName("localization") val localization: String,
    @SerializedName("considerations") val considerations: String
)
data class AppointmentResponse(
    val total: Int,
    val appointments: List<Appointment>
)
data class OneAppointmentResponse(
    val appointment: List<AppointmentArray>
)
data class AppointmentArray(
    val name: String,
    val medic: String,
    val date: String,
    val hour: String,
    val localization: String,
    val considerations: String
)