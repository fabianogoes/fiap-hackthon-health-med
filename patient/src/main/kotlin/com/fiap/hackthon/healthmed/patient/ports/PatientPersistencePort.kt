package com.fiap.hackthon.healthmed.patient.ports

import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import com.fiap.hackthon.healthmed.shared.Email
import java.util.UUID

interface PatientPersistencePort {
    fun create(patient: Patient): Patient
    fun readAll(): List<Patient>
    fun readOneById(id: UUID): Patient?
    fun readOneByEmail(email: Email): Patient?
    fun update(patient: Patient): Patient
    fun delete(id: UUID)
}