package com.mrroboto.notimed.network.responses.identity

data class LoginResponse(val token: String, var error: Int, var errorMessage: String)