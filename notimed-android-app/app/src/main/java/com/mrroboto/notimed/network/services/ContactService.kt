package com.mrroboto.notimed.network.services

import com.mrroboto.notimed.network.responses.contact.ContactRequest
import com.mrroboto.notimed.network.responses.contact.ContactResponse
import com.mrroboto.notimed.network.responses.contact.OneContactResponse
import com.mrroboto.notimed.network.responses.reminder.OneReminderResponse
import com.mrroboto.notimed.network.responses.reminder.ReminderRequest
import com.mrroboto.notimed.network.responses.reminder.ReminderResponse
import retrofit2.http.*

interface ContactService {

    @POST("/contacts/create/{id}")
    suspend fun createContact(
        @Path("id") id: String,
        @Body contactBody: ContactRequest
    )

    @GET("/contacts/{id}?limit=10000")
    suspend fun getContact(@Path("id") id: String) : ContactResponse

    @DELETE("/contacts/delete/{idContact}/{idUser}")
    suspend fun deleteContact(@Path("idContact") idContact: String, @Path("idUser") idUser: String)


    @PATCH("/contacts/update/{idContact}/{idUser}")
    suspend fun updateContact(@Path("idContact") idContact: String, @Path("idUser") idUser: String, @Body credentials: ContactRequest)

    @GET("/contacts/{idContact}/{idUser}")
    suspend fun getOneContact(@Path("idContact") idContact: String, @Path("idUser") idUser: String) : OneContactResponse

}
