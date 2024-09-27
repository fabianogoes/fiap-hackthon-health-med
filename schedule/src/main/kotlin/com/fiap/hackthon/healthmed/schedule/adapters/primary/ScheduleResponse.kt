package com.fiap.hackthon.healthmed.schedule.adapters.primary

import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

data class ScheduleResponse(
    val id: UUID,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val doctorEmail: String,
    val patientEmail: String?,
    val currentState: String,
    val states: List<String>,
)

fun Schedule.toDTO() =
    ScheduleResponse(
        id = this.id,
        doctorEmail = this.slot.doctorEmail.value,
        patientEmail = this.patientEmail?.value,
        date = this.slot.date,
        startTime = this.slot.startTime,
        endTime = this.slot.endTime,
        currentState = this.currentState.state.name,
        states = this.states.map { it.state.name },
    )
