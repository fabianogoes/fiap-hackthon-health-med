package com.fiap.hackthon.healthmed.patient.domain.usecase

import com.fiap.hackthon.healthmed.patient.domain.exception.PatientNotFoundException
import com.fiap.hackthon.healthmed.patient.ports.PatientDeletionPort
import com.fiap.hackthon.healthmed.patient.ports.PatientPersistencePort
import com.fiap.hackthon.healthmed.shared.logger
import jakarta.inject.Named
import java.util.UUID

@Named
class PatientDeletionUseCase(
    private val patientPersistencePort: PatientPersistencePort,
) : PatientDeletionPort {
    private val log = logger<PatientDeletionUseCase>()

    override fun doDelete(id: UUID) {
        log.info("Deleting Patient id: $id")

        return patientPersistencePort
            .readOneById(id)
            ?.let { patientPersistencePort.delete(id) }
            ?: throw PatientNotFoundException(id.toString())
    }

}