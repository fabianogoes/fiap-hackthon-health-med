package com.fiap.hackthon.healthmed.schedule.domain.usecase

import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.schedule.ports.ScheduleCreationPort
import com.fiap.hackthon.healthmed.schedule.ports.SchedulePersistencePort
import com.fiap.hackthon.healthmed.shared.Email
import jakarta.inject.Named
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

@Named
class ScheduleCreationUseCase(
    private val schedulePersistencePort: SchedulePersistencePort
) : ScheduleCreationPort {

    override fun create(doctorEmail: Email, date: LocalDate, startTime: LocalTime, endTime: LocalTime): Schedule {
        val schedule = Schedule(
            id = UUID.randomUUID(),
            date = date,
            startTime = startTime,
            endTime = endTime,
            doctorEmail = doctorEmail,
        ).let(schedulePersistencePort::save)

        return schedule
    }

}