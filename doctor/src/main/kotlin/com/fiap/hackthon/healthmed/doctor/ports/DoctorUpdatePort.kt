package com.fiap.hackthon.healthmed.doctor.ports

import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import java.util.UUID

interface DoctorUpdatePort {
    fun doUpdate(
        id: UUID,
        name: String,
        email: String,
    ): Doctor
}
