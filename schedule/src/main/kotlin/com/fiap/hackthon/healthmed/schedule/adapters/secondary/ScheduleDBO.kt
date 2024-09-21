package com.fiap.hackthon.healthmed.schedule.adapters.secondary

import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.schedule.domain.entity.Slot
import com.fiap.hackthon.healthmed.shared.Email
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import jakarta.persistence.Id
import jakarta.persistence.CascadeType
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import jakarta.persistence.OrderBy
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
        ),
        UniqueConstraint(
            name = "unique_schedule_reserve_constraint",
            columnNames = arrayOf("id", "current_state")
        )
    ]
)
data class ScheduleDBO(
    @Id
    val id: UUID,
    val doctorEmail: String,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
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
        slot = Slot(
            doctorEmail = Email(doctorEmail),
            date = date,
            startTime = startTime,
            endTime = endTime,
        ),
        patientEmail = patientEmail?.let { Email(it) },
        states = states.map { it.toModel() }.toMutableList(),
    )
}

fun Schedule.toDBO() = ScheduleDBO(
    id = id,
    date = slot.date,
    startTime = slot.startTime,
    endTime = slot.endTime,
    doctorEmail = slot.doctorEmail.value,
    patientEmail = patientEmail?.value,
    currentState = currentState.state.name,
).let { dbo ->
    states.map { dbo.states.add(it.toDBO(dbo)) }.toMutableList()
    dbo
}