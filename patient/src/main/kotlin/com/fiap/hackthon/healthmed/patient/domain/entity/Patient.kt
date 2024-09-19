package com.fiap.hackthon.healthmed.patient.domain.entity

import com.fiap.hackthon.healthmed.shared.CPF
import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.user.domain.entity.User
import java.util.UUID

data class Patient(
    val id: UUID,
    val name: String,
    val cpf: CPF,
    val email: Email,

    val user: User,
)
