package com.mrroboto.notimed.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mrroboto.notimed.data.dao.AppointmentDao
import com.mrroboto.notimed.data.dao.ContactDao
import com.mrroboto.notimed.data.dao.ReminderDao
import com.mrroboto.notimed.data.dao.UserDao
import com.mrroboto.notimed.data.models.Appointment
import com.mrroboto.notimed.data.models.Contact
import com.mrroboto.notimed.data.models.Reminder
import com.mrroboto.notimed.data.models.User

@Database(entities = [User::class, Reminder::class, Appointment::class, Contact::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun reminderDao(): ReminderDao
    abstract fun contactDao(): ContactDao
    abstract fun appointmentDao(): AppointmentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "notimed_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}