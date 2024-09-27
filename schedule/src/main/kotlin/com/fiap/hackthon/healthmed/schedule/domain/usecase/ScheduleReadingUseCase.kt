package com.fiap.hackthon.healthmed.schedule.domain.usecase

import com.fiap.hackthon.healthmed.doctor.domain.exception.DoctorNotFoundException
import com.fiap.hackthon.healthmed.doctor.ports.DoctorPersistencePort
import com.fiap.hackthon.healthmed.patient.domain.exception.PatientNotFoundException
import com.fiap.hackthon.healthmed.patient.ports.PatientPersistencePort
import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.schedule.domain.exception.ScheduleNotFoundException
import com.fiap.hackthon.healthmed.schedule.ports.SchedulePersistencePort
import com.fiap.hackthon.healthmed.schedule.ports.ScheduleReadingPort
import com.fiap.hackthon.healthmed.shared.Email
import jakarta.inject.Named
import java.util.UUID

@Named
class ScheduleReadingUseCase(
    private val schedulePersistencePort: SchedulePersistencePort,
    private val doctorPersistencePort: DoctorPersistencePort,
    private val patientPersistencePort: PatientPersistencePort,
) : ScheduleReadingPort {
    override fun readAll(): List<Schedule> {
        return schedulePersistencePort.readAll()
    }

    override fun readById(id: UUID): Schedule {
        return schedulePersistencePort
            .readById(id)
            ?: throw ScheduleNotFoundException(id.toString())
    }

    override fun readAllByDoctor(doctorEmail: Email): List<Schedule> {
        doctorPersistencePort
            .readOneByEmail(doctorEmail)
            ?: throw DoctorNotFoundException(doctorEmail.toString())

        return schedulePersistencePort.readAllByDoctor(doctorEmail)
    }

    override fun readAllByPatient(patientEmail: Email): List<Schedule> {
        patientPersistencePort
            .readOneByEmail(patientEmail)
            ?: throw PatientNotFoundException(patientEmail.toString())

        return schedulePersistencePort.readAllByPatient(patientEmail)
    }

    override fun readAllAvailableByDoctor(doctorEmail: Email): List<Schedule> {
        doctorPersistencePort
            .readOneByEmail(doctorEmail)
            ?: throw DoctorNotFoundException(doctorEmail.toString())

        return schedulePersistencePort
            .readAllByDoctor(doctorEmail)
            .filter { it.isScheduled() }
    }

    override fun readAllReservedByDoctor(doctorEmail: Email): List<Schedule> {
        doctorPersistencePort
            .readOneByEmail(doctorEmail)
            ?: throw DoctorNotFoundException(doctorEmail.toString())

        return schedulePersistencePort
            .readAllByDoctor(doctorEmail)
            .filter { it.isReserved() }
    }
}
