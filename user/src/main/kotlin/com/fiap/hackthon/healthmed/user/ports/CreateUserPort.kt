package com.fiap.hackthon.healthmed.user.ports

import com.fiap.hackthon.healthmed.user.domain.entity.User

interface CreateUserPort {
    fun create(email: String, password: String, role: String): User
}