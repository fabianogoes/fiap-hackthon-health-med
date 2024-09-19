package com.fiap.hackthon.healthmed.user.domain.usecase

import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.user.domain.entity.User
import com.fiap.hackthon.healthmed.user.ports.CreateUserPort
import com.fiap.hackthon.healthmed.user.ports.UserPersistencePort
import jakarta.inject.Named
import org.springframework.security.crypto.password.PasswordEncoder

@Named
class UserCreationUseCase(
    private val userPersistence: UserPersistencePort,
    private val encoder: PasswordEncoder,
) : CreateUserPort {

    override fun create(email: String, password: String, role: String): User {
        val user = User(
            email = Email(email),
            password = encoder.encode(password),
            role = enumValueOf(role),
        )

        return userPersistence.save(user)
    }

}