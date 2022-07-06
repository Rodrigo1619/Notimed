package com.mrroboto.notimed.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders_table")
data class Reminder(
    @PrimaryKey @ColumnInfo("_id") val _id: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("repeatEvery") val repeatEvery: Int,
    @ColumnInfo("hour") val hour: String,
    @ColumnInfo("dose") val dose: Float,
    @ColumnInfo("startDay") val startDay: String,
    @ColumnInfo("endDay") val endDay: String,
    @ColumnInfo("foodOption") val foodOption: Boolean
)