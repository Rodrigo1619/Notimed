package com.mrroboto.notimed.network.dto

data class LoginResponse(val token: String, var error: Int, var errorMessage: String)