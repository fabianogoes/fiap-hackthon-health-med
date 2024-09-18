package com.fiap.hackthon.healthmed.patient.adapters.primary

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class PatientCreationRequest(
    @field:NotBlank(message = "Name must not be blank")
    val name: String,

    @field:NotBlank(message = "CPF must not be blank")
    @field:Pattern(regexp = "\\d{11}", message = "CPF must be exactly 11 digits")
    val cpf: String,

    @field:NotBlank(message = "Email must not be blank")
    @field:Email(message = "Email should be valid")
    val email: String,

    @field:NotBlank(message = "Password must not be blank")
    @field:Size(min = 6, message = "Password must be at least 6 characters long")
    val password: String
)

