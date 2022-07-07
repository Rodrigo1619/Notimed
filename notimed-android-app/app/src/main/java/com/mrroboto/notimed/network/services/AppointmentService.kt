package com.mrroboto.notimed.network.services

import com.mrroboto.notimed.network.responses.appointment.AppointmentRequest
import com.mrroboto.notimed.network.responses.appointment.AppointmentResponse
import com.mrroboto.notimed.network.responses.appointment.OneAppointmentResponse
import retrofit2.http.*

interface AppointmentService {

    //suspend es la equivalencia de async en JS/TS
    @POST("/appointments/create/{id}")
    suspend fun createAppointment(
        @Path("id") id: String,
        @Body appointmentBody:AppointmentRequest
    )
    @GET("/appointments/{id}?limit=10000")
    suspend fun getAppointment(@Path("id")id:String): AppointmentResponse

    @DELETE("/appointments/delete/{idAppointment}/{idUser}")
    suspend fun deleteAppointment(@Path("idAppointment")idAppointment:String,@Path("idUser")idUser:String)

    @PATCH("/appointments/update/{idAppointment}/{idUser}")
    suspend fun updateAppointment(@Path("idAppointment")idAppointment: String, @Path("idUser") idUser: String, @Body credentials: AppointmentRequest)

    @GET("/appointments/{idAppointment}/{idUser}")
    suspend fun getOneAppointment(@Path("idAppointment")idAppointment: String, @Path("idUser")idUser: String): OneAppointmentResponse
}