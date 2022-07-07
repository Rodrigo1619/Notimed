package com.mrroboto.notimed.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointments_table")
data class Appointment(
    @PrimaryKey
    @ColumnInfo("_id") val _id: String,
    @ColumnInfo("name") val Name: String,
    @ColumnInfo("medic") val Medic: String,
    @ColumnInfo("date") val Date: String,
    @ColumnInfo("hour") val Hour: String,
    @ColumnInfo("localization") val Localization: String,
    @ColumnInfo("considerations") val Considerations: String
)