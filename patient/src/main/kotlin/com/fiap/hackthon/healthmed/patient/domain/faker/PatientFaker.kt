package com.fiap.hackthon.healthmed.patient.domain.faker

import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import com.fiap.hackthon.healthmed.shared.cpf
import com.fiap.hackthon.healthmed.shared.email
import com.fiap.hackthon.healthmed.shared.faker
import com.fiap.hackthon.healthmed.shared.password
import com.fiap.hackthon.healthmed.user.domain.entity.User
import java.util.UUID

fun createPatient(): Patient = Patient(
    id = UUID.randomUUID(),
    name = faker.name.firstName(),
    cpf = faker.cpf(),
    email = faker.email(),
    user = User(
        email = faker.email(),
        password = faker.password(),
        role = User.Role.DOCTOR_ROLE,
    )
)