package com.fiap.hackthon.healthmed.doctor.domain.usecase

import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import com.fiap.hackthon.healthmed.doctor.domain.exception.DoctorNotFoundException
import com.fiap.hackthon.healthmed.doctor.domain.ports.DoctorUpdatePort
import com.fiap.hackthon.healthmed.doctor.domain.ports.DoctorPersistencePort
import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.shared.logger
import jakarta.inject.Named
import java.util.UUID

@Named
class DoctorUpdateUseCase(
    private val doctorPersistencePort: DoctorPersistencePort,
) : DoctorUpdatePort {
    private val log = logger<DoctorUpdateUseCase>()

    override fun doUpdate(id: UUID, name: String, email: String): Doctor {
        log.info("Updating Doctor id: $id, name: $name and email: $email")

        return doctorPersistencePort
            .readOne(id)
            ?.let { doctorPersistencePort.update(it.copy(name = name, email = Email(email))) }
            ?: throw DoctorNotFoundException(id)
    }

}