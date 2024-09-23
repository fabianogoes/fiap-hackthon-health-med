package com.fiap.hackthon.healthmed.doctor.domain.usecase

import com.fiap.hackthon.healthmed.doctor.ports.DoctorReadingPort
import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import com.fiap.hackthon.healthmed.doctor.domain.exception.DoctorNotFoundException
import com.fiap.hackthon.healthmed.doctor.ports.DoctorPersistencePort
import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.shared.logger
import jakarta.inject.Named
import java.util.UUID

@Named
class DoctorReadingUseCase(
    private val doctorPersistencePort: DoctorPersistencePort,
): DoctorReadingPort {
    private val log = logger<DoctorCreationUseCase>()

    override fun readAll(): List<Doctor> {
        log.info("Reading all doctors...")

        return doctorPersistencePort.readAll()
    }

    override fun readOne(id: UUID): Doctor {
        log.info("Reading doctor with id: $id")

        return doctorPersistencePort
            .readOneById(id)
            ?: throw DoctorNotFoundException(id.toString())
    }

    override fun readOneByEmail(email: Email): Doctor {
        log.info("Reading doctor with email: $email")

        return doctorPersistencePort
            .readOneByEmail(email)
            ?: throw DoctorNotFoundException(email.value)
    }
}