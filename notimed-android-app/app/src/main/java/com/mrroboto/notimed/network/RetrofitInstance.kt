package com.mrroboto.notimed.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://10.0.2.2:5050/"

object RetrofitInstance {

    private val interceptorLogging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
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
                            .build()
                    )
                }.addInterceptor(interceptorLogging).build()
        ).addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getIdentityServices() : IdentityService {
        return retrofit.create(IdentityService::class.java)
    }
}