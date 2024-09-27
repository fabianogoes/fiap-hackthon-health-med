package com.fiap.hackthon.healthmed.patient.adapters.secondary

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PatientPostgresRepository : JpaRepository<PatientDBO, UUID> {
    fun findByEmail(email: String): PatientDBO?
}
