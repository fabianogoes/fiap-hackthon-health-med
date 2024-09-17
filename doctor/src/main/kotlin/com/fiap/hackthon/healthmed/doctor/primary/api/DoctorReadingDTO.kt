package com.fiap.hackthon.healthmed.doctor.primary.api

import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import java.util.UUID

data class DoctorReadResponse(
    val id: UUID,
    val name: String,
    val cpf: String,
    val email: String,
    val crm: String,
)

fun Doctor.toResponseDTO(): DoctorReadResponse = DoctorReadResponse(
    id = id,
    name = name,
    cpf = cpf.number,
    email = email.value,
    crm = crm.number,
)