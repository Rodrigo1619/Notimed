package com.mrroboto.notimed.network.responses.identity

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("name") val name: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("rol") val role: String = "user"
)
