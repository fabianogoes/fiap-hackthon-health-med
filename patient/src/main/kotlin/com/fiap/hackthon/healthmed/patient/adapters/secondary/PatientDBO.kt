package com.fiap.hackthon.healthmed.patient.adapters.secondary

import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import com.fiap.hackthon.healthmed.shared.CPF
import com.fiap.hackthon.healthmed.shared.Email
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "patients")
data class PatientDBO(
    @Id
    val id: UUID,
    val name: String,

    @Column(unique = true)
    val cpf: String,

    @Column(unique = true)
    val email: String,
) {
    fun toEntity(): Patient = Patient(
        id = id,
        name = name,
        cpf = CPF(cpf),
        email = Email(email),
    )
}

fun Patient.toDBO(): PatientDBO = PatientDBO(
    id = id,
    name = name,
    cpf = cpf.number,
    email = email.value,
)