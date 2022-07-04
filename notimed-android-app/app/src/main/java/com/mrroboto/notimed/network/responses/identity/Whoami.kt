package com.mrroboto.notimed.network.responses.identity

data class WhoamiRequest(val token: String)

data class WhoamiResponse(
    val message: String,
    val content: ContentWhoami,
)

data class ContentWhoami(
    val _id: String,
    val name: String,
    val lastName: String,
    val email: String,
    val birthday: String,
    val gender: String
)