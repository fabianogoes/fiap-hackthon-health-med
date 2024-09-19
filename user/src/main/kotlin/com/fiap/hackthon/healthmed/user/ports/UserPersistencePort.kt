package com.fiap.hackthon.healthmed.user.ports

import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.user.domain.entity.User

interface UserPersistencePort {
    fun save(user: User): User
    fun findByEmail(email: Email): User?
    fun findAll(): List<User>
    fun deleteByEmail(email: Email)
}