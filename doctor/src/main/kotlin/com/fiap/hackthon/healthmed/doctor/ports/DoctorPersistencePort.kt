package com.fiap.hackthon.healthmed.doctor.ports

import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import com.fiap.hackthon.healthmed.shared.Email
import java.util.UUID

interface DoctorPersistencePort {
    fun create(doctor: Doctor): Doctor

    fun readAll(): List<Doctor>

    fun readOneById(id: UUID): Doctor?

    fun readOneByEmail(email: Email): Doctor?

    fun update(doctor: Doctor): Doctor

    fun delete(id: UUID)
}
