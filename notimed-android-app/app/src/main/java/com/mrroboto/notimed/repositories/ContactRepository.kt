package com.mrroboto.notimed.repositories
import androidx.lifecycle.MutableLiveData
import com.mrroboto.notimed.data.AppDatabase
import com.mrroboto.notimed.data.models.Contact
import com.mrroboto.notimed.data.models.Reminder
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.network.responses.contact.ContactRequest
import com.mrroboto.notimed.network.responses.contact.Days
import com.mrroboto.notimed.network.responses.contact.OneContactResponse
import com.mrroboto.notimed.network.responses.reminder.OneReminderResponse
import com.mrroboto.notimed.network.responses.reminder.ReminderRequest
import com.mrroboto.notimed.network.services.ContactService
import com.mrroboto.notimed.network.services.ReminderService
import retrofit2.HttpException

class ContactRepository (
    private val api: ContactService,
    database: AppDatabase,
    private var user_id: String
){
    private val contactDao = database.contactDao()

    suspend fun addContact(
        name: String,
        phoneNumber: String,
        address: String,
        specialization: String,
        startHour: String,
        endHour: String,
        days: Days
    ): ApiResponse<Any> {
        return try {
            val response = api.createContact(
                user_id,
                ContactRequest(name, phoneNumber, address, specialization, startHour, endHour, days)
            )
            ApiResponse.Success(response)
        } catch (err: HttpException) {
            ApiResponse.Failure(err.code(), err.message())
        }
    }

    suspend fun getContacts(): ApiResponse<List<Contact>> {
        return try {
            val response = api.getContact(user_id)

            if (response.total > 0) {
                for (reminders in response.contacts) {
                    contactDao.insertContact(reminders)
                }
            }
            ApiResponse.Success(data = contactDao.getAllContacts())
        } catch (err: HttpException) {
            ApiResponse.Failure(err.code(), err.message())
        }
    }

    suspend fun deleteContact(cardId: String): ApiResponse<Any> {
        return try {
            val response = api.deleteContact(cardId, user_id)
            contactDao.removerContact(cardId)
            ApiResponse.Success(response)
        } catch (err: HttpException) {
            ApiResponse.Failure(err.code(), err.message())
        }
    }

    suspend fun updateContact(
        name: String,
        phoneNumber: String,
        address: String,
        specialization: String,
        startHour: String,
        endHour: String,
        days: Days,
        cardId: String
    ): ApiResponse<Any> {
        return try {
            val response = api.updateContact(cardId, user_id, ContactRequest(name, phoneNumber, address, specialization, startHour, endHour, days))
            contactDao.insertContact(Contact(cardId, name, phoneNumber, address, specialization, startHour, endHour, days, user_id))
            ApiResponse.Success(response)
        } catch (err: HttpException) {
            ApiResponse.Failure(err.code(), err.message())
        }
    }

    suspend fun getOneContact(id: String) : ApiResponse<OneContactResponse> {
        return try {
            val response = api.getOneContact(id, user_id)
            ApiResponse.Success(response)
        } catch (err: HttpException) {
            ApiResponse.Failure(err.code(), err.message())
        }
    }

}
