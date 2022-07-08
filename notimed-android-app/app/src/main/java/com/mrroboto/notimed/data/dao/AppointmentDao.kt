package com.mrroboto.notimed.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrroboto.notimed.data.models.Appointment

@Dao
interface AppointmentDao {
    //Insertando usuario cuando se inicie sesion y sea exitoso
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointment(appointment: Appointment)

    @Query("SELECT * FROM appointments_table")
    suspend fun getAllAppointments(): List<Appointment>

    @Query("DELETE FROM appointments_table WHERE _id LIKE :id")
    suspend fun deleteAppointment(id:String)

    @Query("SELECT * FROM appointments_table WHERE _id LIKE :id")
    suspend fun getAppointment(id:String) : Appointment
}