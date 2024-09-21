package com.fiap.hackthon.healthmed.schedule.domain.usecase

import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.schedule.domain.entity.Slot
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
            slot = Slot(
                doctorEmail = doctorEmail,
                date = date,
                startTime = startTime,
                endTime = endTime,
            ),
        ).let(schedulePersistencePort::save)

        return schedule
    }

}