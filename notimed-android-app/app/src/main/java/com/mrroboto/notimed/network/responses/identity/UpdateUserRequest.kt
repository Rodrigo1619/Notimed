package com.mrroboto.notimed.network.responses.identity

import com.google.gson.annotations.SerializedName

data class UpdateUserRequest(
    @SerializedName("name") val name: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("gender") val gender: String
    )
