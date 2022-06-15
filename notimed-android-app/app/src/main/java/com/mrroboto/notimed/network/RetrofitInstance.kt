package com.mrroboto.notimed.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://10.0.2.2:3000/"

object RetrofitInstance {
    private var token = ""

    private val interceptorLogging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun setToken(value: String) {
        token = value
        println(token)
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
                            .addHeader("Authorization", "BEARER $token")
                            .build()
                    )
                }.addInterceptor(interceptorLogging).build()
        ).addConverterFactory(GsonConverterFactory.create()).build()

    fun getIdentityServices() : IdentityService {
        return retrofit.create(IdentityService::class.java)
    }
}