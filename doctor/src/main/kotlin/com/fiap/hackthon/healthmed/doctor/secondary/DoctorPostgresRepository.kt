package com.fiap.hackthon.healthmed.doctor.secondary

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface DoctorPostgresRepository: JpaRepository<DoctorDBO, UUID>