package com.fiap.hackthon.healthmed.user.domain.entity

import com.fiap.hackthon.healthmed.shared.Email
import java.time.LocalDateTime

data class User(
    val email: Email,
    val password: String,
    val role: Role,
    val lastToken: String? = null,
    val lastTokenAt: LocalDateTime? = null,
) {
    enum class Role {
        DOCTOR_ROLE,
        PATIENT_ROLE,
        ADMIN_ROLE,
    }
}
