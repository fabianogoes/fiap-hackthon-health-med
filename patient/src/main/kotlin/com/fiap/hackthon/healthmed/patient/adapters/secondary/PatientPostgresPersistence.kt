package com.fiap.hackthon.healthmed.patient.adapters.secondary

import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import com.fiap.hackthon.healthmed.patient.ports.PatientPersistencePort
import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.user.ports.UserPersistencePort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class PatientPostgresPersistence(
    private val repository: PatientPostgresRepository,
    private val userPersistencePort: UserPersistencePort,
) : PatientPersistencePort {

    override fun create(patient: Patient): Patient =
        repository
            .save(patient.toDBO())
            .toEntityWithUser()

    override fun readAll(): List<Patient> =
        repository
            .findAll()
            .map { it.toEntityWithUser() }

    override fun readOneById(id: UUID): Patient? =
        repository
            .findByIdOrNull(id)
            ?.toEntityWithUser()

    override fun readOneByEmail(email: Email): Patient? =
        repository.
            findByEmail(email.value)
            ?.toEntityWithUser()

    override fun update(patient: Patient): Patient =
        repository
            .save(patient.toDBO())
            .toEntityWithUser()

    override fun delete(id: UUID) {
        repository.deleteById(id)
    }

    private fun PatientDBO.toEntityWithUser(): Patient {
        val user = userPersistencePort.findByEmail(Email(email))!!
        return toEntity(user)
    }

}