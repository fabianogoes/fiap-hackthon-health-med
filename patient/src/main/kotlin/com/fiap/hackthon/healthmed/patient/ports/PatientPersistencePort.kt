package com.fiap.hackthon.healthmed.patient.ports

import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import java.util.UUID

interface PatientPersistencePort {
    fun create(patient: Patient): Patient
    fun readAll(): List<Patient>
    fun readOne(id: UUID): Patient?
    fun update(patient: Patient): Patient
    fun delete(id: UUID)
}