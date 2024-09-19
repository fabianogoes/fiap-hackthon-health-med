package com.fiap.hackthon.healthmed.user.ports


interface UserDeletePort {
    fun deleteByEmail(email: String)
}