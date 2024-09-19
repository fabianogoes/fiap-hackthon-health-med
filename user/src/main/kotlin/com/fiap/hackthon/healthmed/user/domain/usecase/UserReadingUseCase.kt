package com.fiap.hackthon.healthmed.user.domain.usecase

import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.user.domain.entity.User
import com.fiap.hackthon.healthmed.user.ports.UserPersistencePort
import com.fiap.hackthon.healthmed.user.ports.UserReadingPort
import jakarta.inject.Named

@Named
class UserReadingUseCase(
    private val userPersistencePort: UserPersistencePort,
): UserReadingPort {

    override fun findByEmail(email: String): User? =
        userPersistencePort.findByEmail(Email(email))

    override fun findAll(): List<User> =
        userPersistencePort.findAll()

}