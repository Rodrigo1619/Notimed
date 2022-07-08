package com.mrroboto.notimed.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointments_table")
data class Appointment(
    @PrimaryKey
    @ColumnInfo("_id") val _id: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("medic") val medic: String,
    @ColumnInfo("date") val date: String,
    @ColumnInfo("hour") val hour: String,
    @ColumnInfo("localization") val localization: String,
    @ColumnInfo("considerations") val considerations: String

)