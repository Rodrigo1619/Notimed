package com.mrroboto.notimed.repositories

import com.mrroboto.notimed.data.AppDatabase
import com.mrroboto.notimed.data.models.Reminder
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.network.responses.reminder.ReminderRequest
import com.mrroboto.notimed.network.services.ReminderService
import retrofit2.HttpException

class ReminderRepository(private val api: ReminderService, database: AppDatabase, private var user_id: String) {

    private val reminderDao = database.reminderDao()


    suspend fun addReminder(
        name: String,
        repeatEvery: Int,
        hour: String,
        dose: Float,
        startDay: String,
        endDay: String,
        foodOption: Boolean
    ): ApiResponse<Any> {
        return try {
            val response = api.createReminder(
                user_id,
                ReminderRequest(name, repeatEvery, hour, dose, startDay, endDay, foodOption)
            )
            ApiResponse.Success(response)
        } catch (err: HttpException) {
            ApiResponse.Failure(err.code(), err.message())
        }
    }

    suspend fun getReminders(): ApiResponse<List<Reminder>> {
        return try {
            val response = api.getReminder(user_id)

            if (response.total > 0) {
                for (reminders in response.reminders) {
                    reminderDao.insertReminder(reminders)
                }
            }
            ApiResponse.Success(data = reminderDao.getAllReminders())
        } catch (err: HttpException) {
            ApiResponse.Failure(err.code(), err.message())
        }
    }

    suspend fun deleteReminder(cardId: String) : ApiResponse<Any> {
        return try {
            val response = api.deleteReminder(cardId, user_id)
            reminderDao.removerReminder(cardId)
            ApiResponse.Success(response)
        } catch (err: HttpException) {
            ApiResponse.Failure(err.code(), err.message())
        }
    }
}