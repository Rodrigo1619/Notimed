package com.mrroboto.notimed.network

import com.mrroboto.notimed.network.services.AppointmentService
import com.mrroboto.notimed.network.services.ContactService
import com.mrroboto.notimed.network.services.IdentityService
import com.mrroboto.notimed.network.services.ReminderService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://10.0.2.2:3000"

object RetrofitInstance {

    private val interceptorLogging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private var token = ""

    fun setToken(value: String) {
        token = value
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient()
                .newBuilder()
                .addInterceptor { chain ->
                    chain.proceed(
                        chain
                            .request()
                            .newBuilder()
                            .addHeader("Authorization", "Bearer $token")
                            .build()
                    )
                }.addInterceptor(interceptorLogging).build()
        ).addConverterFactory(GsonConverterFactory.create())
        .build()

    /*Gson es una biblioteca de código abierto para el
    lenguaje de programación Java que permite la serialización y deserialización
    entre objetos Java y su representación en notación JSON*/

    fun getIdentityServices(): IdentityService {
        return retrofit.create(IdentityService::class.java)
    }

    fun getReminderServices(): ReminderService {
        return retrofit.create(ReminderService::class.java)
    }

    fun getAppointmentService(): AppointmentService {
        return retrofit.create(AppointmentService::class.java)
    }

    fun getContactService() : ContactService {
        return retrofit.create(ContactService::class.java)
    }

    // fun get<Service> : <Service> {
    //  return retrofit.create(<Service>::class.java)
    // }
}