package com.fiap.hackthon.healthmed.schedule.adapters.primary

import java.time.LocalDate
import java.time.LocalTime

data class ScheduleCreationRequest(
    val doctorEmail: String,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
)