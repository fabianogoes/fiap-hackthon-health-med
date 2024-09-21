package com.fiap.hackthon.healthmed.schedule.domain.usecase

import com.fiap.hackthon.healthmed.schedule.domain.exception.ScheduleNotFoundException
import com.fiap.hackthon.healthmed.schedule.ports.ScheduleCancellationPort
import com.fiap.hackthon.healthmed.schedule.ports.SchedulePersistencePort
import jakarta.inject.Named
import java.util.UUID

@Named
class ScheduleCancellationUseCase(
    private val schedulePersistencePort: SchedulePersistencePort,
) : ScheduleCancellationPort {

    override fun cancelById(id: UUID) {
        schedulePersistencePort
            .readById(id)
            ?.let { schedulePersistencePort.delete(id) }
            ?: throw ScheduleNotFoundException(id.toString())

        // TODO send email
    }

}