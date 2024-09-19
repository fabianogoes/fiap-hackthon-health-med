package com.fiap.hackthon.healthmed.schedule.adapters.secondary

import com.fiap.hackthon.healthmed.schedule.domain.entity.ScheduleState
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "schedule_state")
data class ScheduleStateDBO(
    @Id
    val id: UUID = UUID.randomUUID(),
    val createdAt: LocalDateTime,

    @ManyToOne(optional = false)
    @JoinColumn(name = "schedule_id")
    val schedule: ScheduleDBO,

    val state: String,
) {

    fun toModel(): ScheduleState = ScheduleState(
        id = id,
        createdAt = createdAt,
        state = enumValueOf(state),
    )
}

fun ScheduleState.toDBO(scheduleDBO: ScheduleDBO): ScheduleStateDBO = ScheduleStateDBO(
    id = id,
    createdAt = createdAt,
    schedule = scheduleDBO,
    state = state.name,
)