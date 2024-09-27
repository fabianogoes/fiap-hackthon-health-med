package com.fiap.hackthon.healthmed.doctor.ports

import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import com.fiap.hackthon.healthmed.shared.Email
import java.util.UUID

interface DoctorReadingPort {
    fun readAll(): List<Doctor>

    fun readOne(id: UUID): Doctor

    fun readOneByEmail(email: Email): Doctor
}
