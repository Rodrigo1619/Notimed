package com.mrroboto.notimed.network.services

import com.mrroboto.notimed.network.responses.reminder.ReminderRequest
import com.mrroboto.notimed.network.responses.reminder.ReminderResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReminderService {

    //suspend es el equivalente a async en JS/TS
    @POST("/reminders/add/{id}")
    suspend fun createReminder(
        @Path("id") id: String,
        @Body reminderBody: ReminderRequest
    )

    @GET("/reminders/{id}")
    suspend fun getReminder(@Path("id") id: String) : ReminderResponse
}