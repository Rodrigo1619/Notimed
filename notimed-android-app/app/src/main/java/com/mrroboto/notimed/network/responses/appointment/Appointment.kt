package com.mrroboto.notimed.network.responses.appointment

import com.google.gson.annotations.SerializedName
import com.mrroboto.notimed.data.models.Appointment

data class AppointmentRequest(
    @SerializedName("appointmentName") val appointmentName: String,
    @SerializedName("doctorName") val doctorName: String,
    @SerializedName("appointmentDate") val appointmentDate: String,
    @SerializedName("appointmentHour") val appointmentHour: String,
    @SerializedName("address") val address: String,
    @SerializedName("additionalNotes") val additionalNotes: String
)
data class AppointmentResponse(
    val total: Int,
    val appointments: List<Appointment>
)
data class OneAppointmentResponse(
    val appointment: AppointmentArray
)
data class AppointmentArray(
    val appointmentName: String,
    val doctorName: String,
    val appointmentDate: String,
    val appointmentHour: String,
    val address: String,
    val additionalNotes: String
)