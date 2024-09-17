package com.fiap.hackthon.healthmed.doctor.adapters.secondary

import com.fiap.hackthon.healthmed.doctor.domain.entity.CRM
import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import com.fiap.hackthon.healthmed.shared.CPF
import com.fiap.hackthon.healthmed.shared.Email
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "doctors")
data class DoctorDBO(
    @Id
    val id: UUID,
    val name: String,
    val cpf: String,
    val email: String,
    val crm: String,
) {
    fun toEntity(): Doctor = Doctor(
        id = id,
        name = name,
        cpf = CPF(cpf),
        email = Email(email),
        crm = CRM(crm)
    )
}

fun Doctor.toDBO(): DoctorDBO = DoctorDBO(
    id = id,
    name = name,
    cpf = cpf.number,
    email = email.value,
    crm = crm.number,
)
