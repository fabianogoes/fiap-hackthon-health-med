package com.fiap.hackthon.healthmed.doctor.secondary

import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import com.fiap.hackthon.healthmed.doctor.domain.ports.DoctorPersistencePort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class DoctorPostgresPersistence(
    private val repository: DoctorPostgresRepository,
) : DoctorPersistencePort {

    override fun create(doctor: Doctor): Doctor =
        repository
            .save(doctor.toDBO())
            .toEntity()

    override fun readAll(): List<Doctor> =
        repository
            .findAll()
            .map(DoctorDBO::toEntity)

    override fun readOne(id: UUID): Doctor? =
        repository
            .findByIdOrNull(id)
            ?.toEntity()

    override fun update(doctor: Doctor): Doctor =
        repository
            .save(doctor.toDBO())
            .toEntity()

    override fun delete(id: UUID) {
        repository.deleteById(id)
    }

}



