package com.mrroboto.notimed.repositories
import com.mrroboto.notimed.data.AppDatabase
import com.mrroboto.notimed.data.models.Contact
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.network.responses.contact.ContactRequest
import com.mrroboto.notimed.network.responses.contact.OneContactResponse
import com.mrroboto.notimed.network.services.ContactService
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
    ): ApiResponse<Any> {
        return try {
            val response = api.createContact(
                user_id,
                ContactRequest(name, phoneNumber, address, specialization, startHour, endHour)
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
                for (contacts in response.contacts) {
                    contactDao.insertContact(contacts)
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
        cardId: String
    ): ApiResponse<Any> {
        return try {
            val response = api.updateContact(cardId, user_id, ContactRequest(name, phoneNumber, address, specialization, startHour, endHour))
            contactDao.insertContact(Contact(cardId, name, phoneNumber, address, specialization, startHour, endHour, user_id))
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
