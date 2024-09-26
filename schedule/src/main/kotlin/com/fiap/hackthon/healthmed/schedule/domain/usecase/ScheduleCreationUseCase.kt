package com.fiap.hackthon.healthmed.schedule.domain.usecase

import com.fiap.hackthon.healthmed.doctor.domain.exception.DoctorNotFoundException
import com.fiap.hackthon.healthmed.doctor.ports.DoctorPersistencePort
import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.schedule.domain.entity.Slot
import com.fiap.hackthon.healthmed.schedule.domain.exception.ScheduleAlreadyExistsException
import com.fiap.hackthon.healthmed.schedule.ports.ScheduleCreationPort
import com.fiap.hackthon.healthmed.schedule.ports.SchedulePersistencePort
import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.shared.logger
import jakarta.inject.Named
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

@Named
class ScheduleCreationUseCase(
    private val schedulePersistencePort: SchedulePersistencePort,
    private val doctorPersistencePort: DoctorPersistencePort,
) : ScheduleCreationPort {
    private val log = logger<ScheduleCreationUseCase>()

    override fun create(doctorEmail: Email, date: LocalDate, startTime: LocalTime, endTime: LocalTime): Schedule {
        log.info("creating schedule doctorEmail: {}, date: {}, startTime: {}, endTime: {}", doctorEmail, date, startTime, endTime)

        doctorPersistencePort
            .readOneByEmail(doctorEmail)
            ?: throw DoctorNotFoundException(doctorEmail.value)

        val slot = Slot(
            doctorEmail = doctorEmail,
            date = date,
            startTime = startTime,
            endTime = endTime,
        )

        if (schedulePersistencePort.existsBySlot(slot))
            throw ScheduleAlreadyExistsException(slot)

        val schedule = Schedule(id = UUID.randomUUID(), slot = slot)
            .let(schedulePersistencePort::save)

        return schedule
    }

}