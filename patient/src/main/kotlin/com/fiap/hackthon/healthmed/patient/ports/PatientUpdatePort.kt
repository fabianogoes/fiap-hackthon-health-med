package com.fiap.hackthon.healthmed.patient.ports

import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import java.util.UUID

interface PatientUpdatePort {
    fun doUpdate(
        id: UUID,
        name: String,
        email: String,
    ): Patient
}
