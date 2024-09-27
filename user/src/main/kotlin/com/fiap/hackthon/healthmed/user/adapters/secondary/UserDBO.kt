package com.fiap.hackthon.healthmed.user.adapters.secondary

import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.user.domain.entity.User
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class UserDBO(
    @Id
    val email: String,
    val password: String,
    val role: String,
    val lastToken: String? = null,
    val lastTokenAt: LocalDateTime? = null,
) {
    fun toEntity(): User =
        User(
            email = Email(email),
            password = password,
            role = enumValueOf(role),
            lastToken = lastToken,
            lastTokenAt = lastTokenAt,
        )
}

fun User.toDBO() =
    UserDBO(
        email = this.email.value,
        password = this.password,
        role = role.name,
        lastToken = lastToken,
        lastTokenAt = lastTokenAt,
    )
