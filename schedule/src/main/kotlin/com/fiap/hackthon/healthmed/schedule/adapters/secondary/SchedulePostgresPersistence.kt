package com.fiap.hackthon.healthmed.schedule.adapters.secondary

import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.schedule.domain.entity.Slot
import com.fiap.hackthon.healthmed.schedule.ports.SchedulePersistencePort
import com.fiap.hackthon.healthmed.shared.Email
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class SchedulePostgresPersistence(
    private val scheduleRepository: SchedulePostgresRepository,
) : SchedulePersistencePort {
    override fun save(schedule: Schedule): Schedule =
        scheduleRepository
            .save(schedule.toDBO())
            .toModel()

    override fun readAll(): List<Schedule> =
        scheduleRepository
            .findAll()
            .map { it.toModel() }

    override fun readAllByDoctor(doctorEmail: Email): List<Schedule> =
        scheduleRepository
            .findAllByDoctorEmailOrderByStartTime(doctorEmail.value)
            .map { it.toModel() }

    override fun readAllByPatient(patientEmail: Email): List<Schedule> =
        scheduleRepository
            .findAllByPatientEmailOrderByStartTime(patientEmail.value)
            .map { it.toModel() }

    override fun readById(id: UUID): Schedule? =
        scheduleRepository
            .findByIdOrNull(id)
            ?.toModel()

    override fun delete(id: UUID) {
        scheduleRepository.deleteById(id)
    }

    override fun existsBySlot(slot: Slot): Boolean =
        scheduleRepository.existsByDoctorEmailAndDateAndStartTimeAndEndTime(
            doctorEmail = slot.doctorEmail.value,
            date = slot.date,
            startTime = slot.startTime,
            endTime = slot.endTime,
        )
}
