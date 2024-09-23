package com.fiap.hackthon.healthmed.patient.domain.usecase

import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import com.fiap.hackthon.healthmed.patient.domain.exception.PatientEmailAlreadyExistsException
import com.fiap.hackthon.healthmed.patient.ports.PatientCreationPort
import com.fiap.hackthon.healthmed.patient.ports.PatientPersistencePort
import com.fiap.hackthon.healthmed.shared.CPF
import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.shared.logger
import com.fiap.hackthon.healthmed.shared.toEmailVO
import com.fiap.hackthon.healthmed.user.domain.entity.User
import com.fiap.hackthon.healthmed.user.ports.CreateUserPort
import jakarta.inject.Named
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Named
open class PatientCreationUseCase(
    private val patientPersistencePort: PatientPersistencePort,
    private val userCreateUserPort: CreateUserPort,
) : PatientCreationPort {
    private val log = logger<PatientCreationUseCase>()

    @Transactional
    override fun doCreate(name: String, cpf: String, email: String, password: String): Patient {
        log.info("Creating name: $name, cpf: $cpf, email: $email, password: $password")

        patientPersistencePort
            .readOneByEmail(email.toEmailVO())
            ?.also { throw PatientEmailAlreadyExistsException(email) }

        val patient = Patient(
            id = UUID.randomUUID(),
            name = name,
            cpf = CPF(cpf),
            email = Email(email),
            user = userCreateUserPort.create(email, password, User.Role.PATIENT_ROLE.name),
        )

        return patientPersistencePort
            .create(patient)
            .also { log.info("Created patient: $it") }
    }
}