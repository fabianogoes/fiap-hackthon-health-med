package com.fiap.hackthon.healthmed.doctor.domain.usecase

import com.fiap.hackthon.healthmed.doctor.domain.entity.CRM
import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import com.fiap.hackthon.healthmed.doctor.domain.exception.DoctorEmailAlreadyExistsException
import com.fiap.hackthon.healthmed.doctor.ports.DoctorCreationPort
import com.fiap.hackthon.healthmed.doctor.ports.DoctorPersistencePort
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
open class DoctorCreationUseCase(
    private val doctorPersistencePort: DoctorPersistencePort,
    private val userCreateUserPort: CreateUserPort,
) : DoctorCreationPort {
    private val log = logger<DoctorCreationUseCase>()

    @Transactional
    override fun doCreate(
        name: String,
        cpf: String,
        email: String,
        crm: String,
        password: String,
    ): Doctor {
        log.info("Creating name: $name, cpf: $cpf, email: $email, crm: $crm, password: $password")

        doctorPersistencePort
            .readOneByEmail(email.toEmailVO())
            ?.also { throw DoctorEmailAlreadyExistsException(email) }

        val doctor =
            Doctor(
                id = UUID.randomUUID(),
                name = name,
                cpf = CPF(cpf),
                email = Email(email),
                crm = CRM(crm),
                user = userCreateUserPort.create(email, password, User.Role.DOCTOR_ROLE.name),
            )

        return doctorPersistencePort
            .create(doctor)
            .also { log.info("Created doctor: $it") }
    }
}
