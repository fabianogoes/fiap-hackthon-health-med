package com.fiap.hackthon.healthmed.schedule.adapters.secondary

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

@Repository
interface SchedulePostgresRepository : JpaRepository<ScheduleDBO, UUID> {
    fun findAllByDoctorEmailOrderByStartTime(doctorEmail: String): List<ScheduleDBO>

    fun findAllByPatientEmailOrderByStartTime(patientEmail: String): List<ScheduleDBO>

    fun existsByDoctorEmailAndDateAndStartTimeAndEndTime(
        doctorEmail: String,
        date: LocalDate,
        startTime: LocalTime,
        endTime: LocalTime,
    ): Boolean
}
