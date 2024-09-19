package com.fiap.hackthon.healthmed.schedule.ports

import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.shared.Email
import java.util.UUID

interface ScheduleReadingPort {
    fun readAll(): List<Schedule>
    fun readById(id: UUID): Schedule
    fun readAllByDoctor(doctorEmail: Email): List<Schedule>
    fun readAllByPatient(patientEmail: Email): List<Schedule>
    fun readAllAvailableByDoctor(doctorEmail: Email): List<Schedule>
    fun readAllReservedByDoctor(doctorEmail: Email): List<Schedule>
}