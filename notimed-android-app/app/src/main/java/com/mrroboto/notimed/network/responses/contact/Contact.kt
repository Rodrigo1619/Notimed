package com.mrroboto.notimed.network.responses.contact

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mrroboto.notimed.data.models.Contact

data class ContactRequest(
    @SerializedName("name") val name: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("address") val address: String,
    @SerializedName("specialization") val specialization: String,
    @SerializedName("startHour") val startHour: String,
    @SerializedName("endHour") val endHour: String,
    @SerializedName("days") val days: Days
)

data class ContactResponse(
    val total: Int,
    val contacts: List<Contact>
)

data class OneContactResponse(
    val contact: List<ContactArray>
)

data class ContactArray(
    val name: String,
    val phoneNumber: String,
    val address: String,
    val specialization: String,
    val startHour: String,
    val endHour: String,
    val days: Days
)

data class Days(
    val monday: Boolean,
    val tuesday: Boolean,
    val wednesday: Boolean,
    val thursday: Boolean,
    val friday: Boolean,
    val saturday: Boolean,
    val sunday: Boolean
)
