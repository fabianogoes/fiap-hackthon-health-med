package com.fiap.hackthon.healthmed.schedule.domain.entity

import com.fiap.hackthon.healthmed.shared.Email
import java.time.LocalDate
import java.time.LocalTime

data class Slot(
    val doctorEmail: Email,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
)
