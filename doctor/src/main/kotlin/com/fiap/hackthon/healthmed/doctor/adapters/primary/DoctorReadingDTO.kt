package com.fiap.hackthon.healthmed.doctor.adapters.primary

import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import java.util.UUID

data class DoctorReadResponse(
    val id: UUID,
    val name: String,
    val cpf: String,
    val email: String,
    val crm: String,
    val userRole: String,
)

fun Doctor.toResponseDTO(): DoctorReadResponse =
    DoctorReadResponse(
        id = id,
        name = name,
        cpf = cpf.number,
        email = email.value,
        crm = crm.number,
        userRole = user.role.name,
    )
