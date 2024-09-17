package com.fiap.hackthon.healthmed.doctor.ports

import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import java.util.UUID

interface DoctorPersistencePort {
    fun create(doctor: Doctor): Doctor
    fun readAll(): List<Doctor>
    fun readOne(id: UUID): Doctor?
    fun update(doctor: Doctor): Doctor
    fun delete(id: UUID)
}