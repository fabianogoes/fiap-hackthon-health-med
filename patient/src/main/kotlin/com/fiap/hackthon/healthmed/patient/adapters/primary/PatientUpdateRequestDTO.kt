package com.fiap.hackthon.healthmed.patient.adapters.primary

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class PatientUpdateRequestDTO(
    @field:NotBlank(message = "Name must not be blank")
    val name: String,
    @field:NotBlank(message = "Email must not be blank")
    @field:Email(message = "Email should be valid")
    val email: String,
)
