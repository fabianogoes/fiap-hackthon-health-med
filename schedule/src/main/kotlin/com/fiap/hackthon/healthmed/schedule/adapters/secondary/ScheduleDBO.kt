package com.fiap.hackthon.healthmed.schedule.adapters.secondary

import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.shared.Email
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

@Entity
@Table(
    name = "schedules",
    uniqueConstraints = [
        UniqueConstraint(
            name = "unique_schedule_constraint",
            columnNames = arrayOf("doctor_email", "date", "start_time", "end_time")
        )
    ]
)
data class ScheduleDBO(
    @Id
    val id: UUID,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val doctorEmail: String,
    val patientEmail: String? = null,
    val currentState: String,

    @OneToMany(
        mappedBy = "schedule",
        cascade = [CascadeType.MERGE, CascadeType.REMOVE],
        fetch = FetchType.EAGER,
    )
    @OrderBy(value = "createdAt ASC")
    val states: MutableList<ScheduleStateDBO> = mutableListOf(),
) {

    fun toModel() = Schedule(
        id = id,
        date = date,
        startTime = startTime,
        endTime = endTime,
        doctorEmail = Email(doctorEmail),
        patientEmail = patientEmail?.let { Email(it) },
        states = states.map { it.toModel() }.toMutableList(),
    )
}

fun Schedule.toDBO() = ScheduleDBO(
    id = id,
    date = date,
    startTime = startTime,
    endTime = endTime,
    doctorEmail = doctorEmail.value,
    patientEmail = patientEmail?.value,
    currentState = currentState.state.name,
).let { dbo ->
    states.map { dbo.states.add(it.toDBO(dbo)) }.toMutableList()
    dbo
}