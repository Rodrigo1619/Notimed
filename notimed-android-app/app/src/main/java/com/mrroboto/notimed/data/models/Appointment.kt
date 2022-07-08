package com.mrroboto.notimed.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointments_table")
data class Appointment(
    @PrimaryKey
    @ColumnInfo("_id") val _id: String,
    @ColumnInfo("appointmentName") val appointmentName: String,
    @ColumnInfo("doctorName") val doctorName: String,
    @ColumnInfo("appointmentDate") val appointmentDate: String,
    @ColumnInfo("appointmentHour") val appointmentHour: String,
    @ColumnInfo("address") val address: String,
    @ColumnInfo("additionalNotes") val additionalNotes: String,
    @ColumnInfo("user") val user: String
)