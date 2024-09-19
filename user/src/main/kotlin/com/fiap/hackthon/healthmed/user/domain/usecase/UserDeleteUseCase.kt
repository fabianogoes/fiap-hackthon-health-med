package com.fiap.hackthon.healthmed.user.domain.usecase

import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.user.ports.UserDeletePort
import com.fiap.hackthon.healthmed.user.ports.UserPersistencePort
import jakarta.inject.Named

@Named
class UserDeleteUseCase(
    private val userPersistencePort: UserPersistencePort,
) : UserDeletePort {

    override fun deleteByEmail(email: String) {
        userPersistencePort.deleteByEmail(Email(email))
    }

}