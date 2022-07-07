package com.mrroboto.notimed.repositories

import androidx.lifecycle.MutableLiveData
import com.mrroboto.notimed.data.AppDatabase
import com.mrroboto.notimed.data.models.Appointment
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.network.responses.appointment.AppointmentRequest
import com.mrroboto.notimed.network.responses.appointment.OneAppointmentResponse
import com.mrroboto.notimed.network.services.AppointmentService
import retrofit2.HttpException

class AppointmentRepository (
    private val api: AppointmentService,
    database: AppDatabase,
    private var user_id: String
){

    private val appointmentDao = database.appointmentDao()

    suspend fun addAppointment(
        name:String,
        medic:String,
        date:String,
        hour:String,
        localization:String,
        considerations: String
    ):ApiResponse<Any>{
        return try{
            val response = api.createAppointment(
                user_id,
                AppointmentRequest(name, medic, date, hour, localization, considerations)
            )
            ApiResponse.Success(response)
        }catch (err: HttpException){
            ApiResponse.Failure(err.code(),err.message())
        }
    }

    suspend fun getAppointments():ApiResponse<List<Appointment>>{
        return try{
            val response = api.getAppointment(user_id)

            if(response.total>0){
                for(appointments in response.appointments){
                    appointmentDao.insertAppointment(appointments)
                }
            }
            ApiResponse.Success(data = appointmentDao.getAllAppointments())
        }catch (err:HttpException){
            ApiResponse.Failure(err.code(), err.message())
        }
    }

    suspend fun deleteAppointment(cardId: String): ApiResponse<Any>{
        return try{
            val response = api.deleteAppointment(cardId, user_id)
            appointmentDao.deleteAppointment(cardId)
            ApiResponse.Success(response)
        }catch (err: HttpException){
            ApiResponse.Failure(err.code(), err.message())
        }
    }

    suspend fun updateAppointment(
        name: String,
        medic: String,
        date: String,
        hour: String,
        localization: String,
        considerations: String,
        cardId:String
    ): ApiResponse<Any>{
        return try{
            val response = api.updateAppointment(cardId, user_id, AppointmentRequest(name,medic,date,hour,localization,considerations))
            appointmentDao.insertAppointment(Appointment(cardId,name, medic, date, hour, localization, considerations))
            ApiResponse.Success(response)
        }catch (err: HttpException){
            ApiResponse.Failure(err.code(), err.message())
        }
    }
    suspend fun getOneAppointment(id:String):ApiResponse<OneAppointmentResponse>{
        return try{
            val response = api.getOneAppointment(id, user_id)
            ApiResponse.Success(response)
        }catch (err: HttpException){
            ApiResponse.Failure(err.code(), err.message())
        }
    }
}

