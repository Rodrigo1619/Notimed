package com.mrroboto.notimed.network.responses.reminder

import com.google.gson.annotations.SerializedName
import com.mrroboto.notimed.data.models.Reminder

data class ReminderRequest(
    @SerializedName("name") val name: String,
    @SerializedName("repeatEvery") val repeatEvery: Int,
    @SerializedName("hour") val hour: String,
    @SerializedName("dose") val dose: Float,
    @SerializedName("startDay") val startDay: String,
    @SerializedName("endDay") val endDay: String,
    @SerializedName("foodOption") val foodOption: Boolean
)

data class ReminderResponse(
    val total: Int,
    val reminders: List<Reminder>
)
