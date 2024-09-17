package com.fiap.hackthon.healthmed.doctor.domain.usecase

import com.fiap.hackthon.healthmed.doctor.domain.entity.CRM
import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import com.fiap.hackthon.healthmed.doctor.ports.DoctorCreationPort
import com.fiap.hackthon.healthmed.doctor.ports.DoctorPersistencePort
import com.fiap.hackthon.healthmed.shared.CPF
import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.shared.logger
import jakarta.inject.Named
import java.util.UUID

@Named
class DoctorCreationUseCase(
    private val doctorPersistencePort: DoctorPersistencePort,
) : DoctorCreationPort {
    private val log = logger<DoctorCreationUseCase>()

    override fun doCreate(name: String, cpf: String, email: String, crm: String, password: String): Doctor {
        log.info("Creating $name $cpf $email $crm $password")

        val doctor = Doctor(
            id = UUID.randomUUID(),
            name = name,
            cpf = CPF(cpf),
            email = Email(email),
            crm = CRM(crm),
        )

        return doctorPersistencePort
            .create(doctor)
            .also { log.info("Created doctor: $it") }
    }
}