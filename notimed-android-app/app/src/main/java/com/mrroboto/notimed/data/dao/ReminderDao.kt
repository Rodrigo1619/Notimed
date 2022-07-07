package com.mrroboto.notimed.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrroboto.notimed.data.models.Reminder

@Dao
interface ReminderDao {
    // Insertar usuario cuando se inicie sesion y sea exitoso
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: Reminder)

    @Query("SELECT * FROM reminders_table")
    suspend fun getAllReminders() : List<Reminder>

    @Query("DELETE FROM reminders_table WHERE _id LIKE :id")
    suspend fun removerReminder(id: String)

    @Query("SELECT * FROM reminders_table WHERE _id LIKE :id")
    suspend fun getReminder(id: String) : Reminder
}