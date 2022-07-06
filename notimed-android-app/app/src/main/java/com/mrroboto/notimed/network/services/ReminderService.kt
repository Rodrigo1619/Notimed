package com.mrroboto.notimed.network.services

import com.mrroboto.notimed.network.responses.reminder.ReminderRequest
import com.mrroboto.notimed.network.responses.reminder.ReminderResponse
import retrofit2.http.*

interface ReminderService {

    //suspend es el equivalente a async en JS/TS
    @POST("/reminders/add/{id}")
    suspend fun createReminder(
        @Path("id") id: String,
        @Body reminderBody: ReminderRequest
    )

    @GET("/reminders/{id}?limit=10000")
    suspend fun getReminder(@Path("id") id: String) : ReminderResponse

    @DELETE("/reminders/delete/{idReminder}/{idUser}")
    suspend fun deleteReminder(@Path("idReminder") idReminder: String, @Path("idUser") idUser: String)

    @PATCH("/reminders/updaye/{idReminder}/{idUser}")
    suspend fun updateReminder(@Path("idReminder") idReminder: String, @Path("idUser") idUser: String, @Body credentials:  ReminderRequest)
}