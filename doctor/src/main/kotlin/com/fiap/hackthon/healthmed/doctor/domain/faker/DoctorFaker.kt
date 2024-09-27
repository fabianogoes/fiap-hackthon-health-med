package com.fiap.hackthon.healthmed.doctor.domain.faker

import com.fiap.hackthon.healthmed.doctor.domain.entity.CRM
import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import com.fiap.hackthon.healthmed.shared.cpf
import com.fiap.hackthon.healthmed.shared.crm
import com.fiap.hackthon.healthmed.shared.email
import com.fiap.hackthon.healthmed.shared.faker
import com.fiap.hackthon.healthmed.shared.password
import com.fiap.hackthon.healthmed.user.domain.entity.User
import java.util.UUID

fun createDoctor(): Doctor =
    Doctor(
        id = UUID.randomUUID(),
        name = faker.name.firstName(),
        cpf = faker.cpf(),
        email = faker.email(),
        crm = CRM(faker.crm()),
        user =
            User(
                email = faker.email(),
                password = faker.password(),
                role = User.Role.DOCTOR_ROLE,
            ),
    )
