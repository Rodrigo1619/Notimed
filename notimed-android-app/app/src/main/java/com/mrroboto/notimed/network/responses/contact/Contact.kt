package com.mrroboto.notimed.network.responses.contact

import com.google.gson.annotations.SerializedName
import com.mrroboto.notimed.data.models.Contact

data class ContactRequest(
    @SerializedName("name") val name: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("address") val address: String,
    @SerializedName("specialization") val specialization: String,
    @SerializedName("startHour") val startHour: String,
    @SerializedName("endHour") val endHour: String,
)

data class ContactResponse(
    val total: Int,
    val contacts: List<Contact>
)

data class OneContactResponse(
    val contact: ContactArray
)

data class ContactArray(
    val name: String,
    val phoneNumber: String,
    val address: String,
    val specialization: String,
    val startHour: String,
    val endHour: String
)
