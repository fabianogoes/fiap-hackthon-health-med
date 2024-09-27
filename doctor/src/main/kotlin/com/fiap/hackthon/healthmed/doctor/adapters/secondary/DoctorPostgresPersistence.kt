package com.fiap.hackthon.healthmed.doctor.adapters.secondary

import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import com.fiap.hackthon.healthmed.doctor.ports.DoctorPersistencePort
import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.user.ports.UserPersistencePort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class DoctorPostgresPersistence(
    private val doctorRepository: DoctorPostgresRepository,
    private val userPersistencePort: UserPersistencePort,
) : DoctorPersistencePort {
    override fun create(doctor: Doctor): Doctor =
        doctorRepository
            .save(doctor.toDBO())
            .toEntityWithUser()

    override fun readAll(): List<Doctor> =
        doctorRepository
            .findAll()
            .map { it.toEntityWithUser() }

    override fun readOneById(id: UUID): Doctor? =
        doctorRepository
            .findByIdOrNull(id)
            ?.toEntityWithUser()

    override fun readOneByEmail(email: Email): Doctor? =
        doctorRepository
            .findByEmail(email.value)
            ?.toEntityWithUser()

    override fun update(doctor: Doctor): Doctor =
        doctorRepository
            .save(doctor.toDBO())
            .toEntityWithUser()

    override fun delete(id: UUID) {
        doctorRepository.deleteById(id)
    }

    private fun DoctorDBO.toEntityWithUser(): Doctor {
        val user = userPersistencePort.findByEmail(Email(email))!!
        return toEntity(user)
    }
}
