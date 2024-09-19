package com.fiap.hackthon.healthmed.schedule.domain.usecase

import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.schedule.ports.SchedulePersistencePort
import com.fiap.hackthon.healthmed.schedule.ports.ScheduleReadingPort
import com.fiap.hackthon.healthmed.shared.Email
import jakarta.inject.Named
import java.util.UUID

@Named
class ScheduleReadingUseCase(
    private val schedulePersistencePort: SchedulePersistencePort,
): ScheduleReadingPort {

    override fun readAll(): List<Schedule> {
        return schedulePersistencePort.readAll()
    }

    override fun readById(id: UUID): Schedule {
        return schedulePersistencePort
            .readById(id)
            ?: throw RuntimeException("Schedule with id $id not found")
    }

    override fun readAllByDoctor(doctorEmail: Email): List<Schedule> {
        return schedulePersistencePort.readAllByDoctor(doctorEmail)
    }

    override fun readAllByPatient(patientEmail: Email): List<Schedule> {
        return schedulePersistencePort.readAllByPatient(patientEmail)
    }

    override fun readAllAvailableByDoctor(doctorEmail: Email): List<Schedule> {
        return schedulePersistencePort
            .readAllByDoctor(doctorEmail)
            .filter { it.isScheduled() }
    }

    override fun readAllReservedByDoctor(doctorEmail: Email): List<Schedule> {
        return schedulePersistencePort
            .readAllByDoctor(doctorEmail)
            .filter { it.isReserved() }
    }

}