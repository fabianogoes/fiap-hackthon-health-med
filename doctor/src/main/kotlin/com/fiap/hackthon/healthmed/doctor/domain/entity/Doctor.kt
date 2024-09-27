package com.fiap.hackthon.healthmed.doctor.domain.entity

import com.fiap.hackthon.healthmed.shared.CPF
import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.user.domain.entity.User
import java.util.UUID

data class Doctor(
    val id: UUID,
    val name: String,
    val cpf: CPF,
    val email: Email,
    val crm: CRM,
    val user: User,
)
