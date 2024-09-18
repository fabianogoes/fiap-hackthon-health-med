package com.fiap.hackthon.healthmed.patient.ports

import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import java.util.UUID

interface PatientReadingPort {
    fun readAll(): List<Patient>
    fun readOne(id: UUID): Patient
}