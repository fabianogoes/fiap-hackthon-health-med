package com.fiap.hackthon.healthmed.doctor.ports

import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor

interface DoctorCreationPort {
    fun doCreate(
        name: String,
        cpf: String,
        email: String,
        crm: String,
        password: String,
    ): Doctor
}
