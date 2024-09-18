package com.fiap.hackthon.healthmed.patient.adapters.secondary

import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import com.fiap.hackthon.healthmed.patient.ports.PatientPersistencePort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class PatientPostgresPersistence(
    private val repository: PatientPostgresRepository,
) : PatientPersistencePort {

    override fun create(patient: Patient): Patient =
        repository
            .save(patient.toDBO())
            .toEntity()

    override fun readAll(): List<Patient> =
        repository
            .findAll()
            .map(PatientDBO::toEntity)

    override fun readOne(id: UUID): Patient? =
        repository
            .findByIdOrNull(id)
            ?.toEntity()

    override fun update(patient: Patient): Patient =
        repository
            .save(patient.toDBO())
            .toEntity()

    override fun delete(id: UUID) {
        repository.deleteById(id)
    }

}