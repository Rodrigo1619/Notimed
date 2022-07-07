package com.mrroboto.notimed.network.responses.reminder

import com.google.gson.annotations.SerializedName
import com.mrroboto.notimed.data.models.Reminder

data class ReminderRequest(
    @SerializedName("name") val name: String,
    @SerializedName("repeatEvery") val repeatEvery: Int,
    @SerializedName("hour") val hour: String,
    @SerializedName("dose") val dose: Int,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("endDate") val endDate: String,
    @SerializedName("foodOption") val foodOption: Boolean
)

data class ReminderResponse(
    val total: Int,
    val reminders: List<Reminder>
)

data class OneReminderResponse(
    val reminder: List<ReminderArray>
)

data class ReminderArray(
    val name: String,
    val repeatEvery: Int,
    val hour: String,
    val dose: Int,
    val startDate: String,
    val endDate: String,
    val foodOption: Boolean
)
