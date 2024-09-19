package com.fiap.hackthon.healthmed.schedule.domain.usecase

import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.schedule.ports.SchedulePersistencePort
import com.fiap.hackthon.healthmed.schedule.ports.ScheduleReservationPort
import com.fiap.hackthon.healthmed.shared.Email
import jakarta.inject.Named
import java.util.UUID

@Named
class ScheduleReservationUseCase(
    private val schedulePersistencePort: SchedulePersistencePort,
): ScheduleReservationPort {

    override fun reserve(id: UUID, patientEmail: Email): Schedule {
        val schedule = schedulePersistencePort
            .readById(id)
            ?: throw RuntimeException("Schedule with id $id not found")

        val reserved = schedulePersistencePort
            .save(schedule.copy(patientEmail = patientEmail).reserved())

        // TODO send email to doctor and patient

        return reserved
    }

}