package com.fiap.hackthon.healthmed.schedule.domain.entity

import com.fiap.hackthon.healthmed.schedule.domain.entity.ScheduleState.State
import com.fiap.hackthon.healthmed.shared.Email
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

data class Schedule(
    val id: UUID,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val doctorEmail: Email,
    val patientEmail: Email? = null,
    val states: MutableList<ScheduleState> = mutableListOf(ScheduleState()),
) {
    val currentState: ScheduleState
        get() = states.maxByOrNull { it.createdAt } ?: throw IllegalStateException("Unknown schedule state")

    fun reserved(): Schedule {
        addState(State.RESERVED)
        return this
    }
    fun received(): Schedule {
        addState(State.RECEIVED)
        return this
    }

    fun answered(): Schedule {
        addState(State.ANSWERED)
        return this
    }

    fun canceled(): Schedule {
        addState(State.CANCELLED)
        return this
    }

    fun isScheduled() = currentState.state == State.SCHEDULED
    fun isReserved() = currentState.state == State.RESERVED
    fun isReceived() = currentState.state == State.RECEIVED
    fun isAnswered() = currentState.state == State.ANSWERED
    fun isCanceled() = currentState.state == State.CANCELLED

    private fun addState(state: State): ScheduleState =
        ScheduleState(state = state)
            .let {
                states.add(it)
                it
            }
}


