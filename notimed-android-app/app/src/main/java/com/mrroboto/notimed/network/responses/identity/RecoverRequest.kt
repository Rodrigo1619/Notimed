package com.mrroboto.notimed.network.responses.identity

import com.google.gson.annotations.SerializedName

data class RecoverRequest(
    @SerializedName("email") val email: String
    )

