package com.fiap.hackthon.healthmed.user.ports

interface AuthenticationPort {
    fun authenticate(email: String, password: String): String
    fun refreshAccessToken(refreshToken: String): String?
}