package com.fiap.hackthon.healthmed.schedule.ports

import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.shared.Email
import java.util.UUID

interface ScheduleReservationPort {
    fun reserve(id: UUID, patientEmail: Email): Schedule
}