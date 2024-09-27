package com.fiap.hackthon.healthmed.patient.domain.usecase

import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import com.fiap.hackthon.healthmed.patient.domain.exception.PatientNotFoundException
import com.fiap.hackthon.healthmed.patient.ports.PatientPersistencePort
import com.fiap.hackthon.healthmed.patient.ports.PatientUpdatePort
import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.shared.logger
import jakarta.inject.Named
import java.util.UUID

@Named
class PatientUpdateUseCase(
    private val patientPersistencePort: PatientPersistencePort,
) : PatientUpdatePort {
    private val log = logger<PatientUpdateUseCase>()

    override fun doUpdate(
        id: UUID,
        name: String,
        email: String,
    ): Patient {
        log.info("Updating Patient id: $id, name: $name and email: $email")

        return patientPersistencePort
            .readOneById(id)
            ?.let { patientPersistencePort.update(it.copy(name = name, email = Email(email))) }
            ?: throw PatientNotFoundException(id.toString())
    }
}
