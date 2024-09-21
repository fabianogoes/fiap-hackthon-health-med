package com.fiap.hackthon.healthmed.doctor.domain.usecase

import com.fiap.hackthon.healthmed.doctor.domain.exception.DoctorNotFoundException
import com.fiap.hackthon.healthmed.doctor.ports.DoctorDeletionPort
import com.fiap.hackthon.healthmed.doctor.ports.DoctorPersistencePort
import com.fiap.hackthon.healthmed.shared.logger
import jakarta.inject.Named
import java.util.UUID

@Named
class DoctorDeletionUseCase(
    private val doctorPersistencePort: DoctorPersistencePort,
) : DoctorDeletionPort {
    private val log = logger<DoctorDeletionUseCase>()

    override fun doDelete(id: UUID) {
        log.info("Deleting Doctor id: $id")

        return doctorPersistencePort
            .readOneById(id)
            ?.let { doctorPersistencePort.delete(id) }
            ?: throw DoctorNotFoundException(id.toString())
    }

}