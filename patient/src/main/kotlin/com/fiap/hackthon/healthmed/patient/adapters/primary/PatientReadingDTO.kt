package com.fiap.hackthon.healthmed.patient.adapters.primary

import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import java.util.UUID

data class PatientReadResponse(
    val id: UUID,
    val name: String,
    val cpf: String,
    val email: String,
)

fun Patient.toResponseDTO(): PatientReadResponse = PatientReadResponse(
    id = id,
    name = name,
    cpf = cpf.number,
    email = email.value,
)