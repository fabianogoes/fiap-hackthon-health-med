package com.fiap.hackthon.healthmed.schedule.domain.faker

import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.schedule.domain.entity.Slot
import com.fiap.hackthon.healthmed.shared.Email
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

fun createSchedule(
    doctorEmail: Email,
    date: LocalDate = LocalDate.now(),
    startTime: LocalTime = LocalTime.of(10, 0),
    endTime: LocalTime = startTime.withHour(1),
    patientEmail: Email? = null,
) = Schedule(
    id = UUID.randomUUID(),
    slot = Slot(
        doctorEmail = doctorEmail,
        date = date,
        startTime = startTime,
        endTime = endTime,
    ),
    patientEmail = patientEmail,
)