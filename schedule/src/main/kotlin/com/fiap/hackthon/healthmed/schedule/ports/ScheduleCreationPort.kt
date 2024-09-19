package com.fiap.hackthon.healthmed.schedule.ports

import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.shared.Email
import java.time.LocalDate
import java.time.LocalTime

interface ScheduleCreationPort {
    fun create(doctorEmail: Email, date: LocalDate, startTime: LocalTime, endTime: LocalTime): Schedule
}