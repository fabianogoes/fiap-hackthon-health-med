package com.fiap.hackthon.healthmed.user.ports

import com.fiap.hackthon.healthmed.user.domain.entity.User

interface UserReadingPort {
    fun findByEmail(email: String): User?

    fun findAll(): List<User>
}
