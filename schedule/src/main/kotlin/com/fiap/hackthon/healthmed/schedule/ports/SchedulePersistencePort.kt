package com.fiap.hackthon.healthmed.schedule.ports

import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.schedule.domain.entity.Slot
import com.fiap.hackthon.healthmed.shared.Email
import java.util.UUID

interface SchedulePersistencePort {
    fun save(schedule: Schedule): Schedule

    fun readAll(): List<Schedule>

    fun readAllByDoctor(doctorEmail: Email): List<Schedule>

    fun readAllByPatient(patientEmail: Email): List<Schedule>

    fun readById(id: UUID): Schedule?

    fun delete(id: UUID)

    fun existsBySlot(slot: Slot): Boolean
}
