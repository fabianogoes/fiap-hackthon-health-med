package com.fiap.hackthon.healthmed.doctor.adapters.secondary

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface DoctorPostgresRepository : JpaRepository<DoctorDBO, UUID> {
    fun findByEmail(email: String): DoctorDBO?
}
