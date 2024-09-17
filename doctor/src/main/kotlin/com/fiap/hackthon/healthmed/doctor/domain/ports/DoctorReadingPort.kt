package com.fiap.hackthon.healthmed.doctor.domain.ports

import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import java.util.UUID

interface DoctorReadingPort {
    fun readAll(): List<Doctor>
    fun readOne(id: UUID): Doctor
}