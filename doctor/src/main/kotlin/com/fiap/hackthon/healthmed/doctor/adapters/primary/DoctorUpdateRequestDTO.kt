package com.fiap.hackthon.healthmed.doctor.adapters.primary

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class DoctorUpdateRequestDTO(
    @field:NotBlank(message = "Name must not be blank")
    val name: String,
    @field:NotBlank(message = "Email must not be blank")
    @field:Email(message = "Email should be valid")
    val email: String,
)
