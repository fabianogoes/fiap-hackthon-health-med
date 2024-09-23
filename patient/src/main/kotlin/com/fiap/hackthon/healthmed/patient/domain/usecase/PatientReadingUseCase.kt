package com.fiap.hackthon.healthmed.patient.domain.usecase

import com.fiap.hackthon.healthmed.patient.ports.PatientReadingPort
import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import com.fiap.hackthon.healthmed.patient.domain.exception.PatientNotFoundException
import com.fiap.hackthon.healthmed.patient.ports.PatientPersistencePort
import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.shared.logger
import jakarta.inject.Named
import java.util.UUID

@Named
class PatientReadingUseCase(
    private val patientPersistencePort: PatientPersistencePort,
): PatientReadingPort {
    private val log = logger<PatientCreationUseCase>()

    override fun readAll(): List<Patient> {
        log.info("Reading all patients...")

        return patientPersistencePort.readAll()
    }

    override fun readOne(id: UUID): Patient {
        log.info("Reading patient with id: $id")

        return patientPersistencePort
            .readOneById(id)
            ?: throw PatientNotFoundException(id.toString())
    }

    override fun readOneByEmail(email: Email): Patient {
        log.info("Reading patient with email: $email")

        return patientPersistencePort
            .readOneByEmail(email)
            ?: throw PatientNotFoundException(email.value)
    }
}