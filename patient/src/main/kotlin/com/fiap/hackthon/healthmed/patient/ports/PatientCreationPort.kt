package com.fiap.hackthon.healthmed.patient.ports

import com.fiap.hackthon.healthmed.patient.domain.entity.Patient

interface PatientCreationPort {
    fun doCreate(
        name: String,
        cpf: String,
        email: String,
        password: String,
    ): Patient
}
