package com.fiap.hackthon.healthmed.schedule.domain.entity

import com.fiap.hackthon.healthmed.schedule.domain.entity.ScheduleState.State
import com.fiap.hackthon.healthmed.shared.Email
import java.util.UUID

data class Schedule(
    val id: UUID,
    val slot: Slot,
    val patientEmail: Email? = null,
    val states: MutableList<ScheduleState> = mutableListOf(ScheduleState()),
) {
    val currentState: ScheduleState
        get() = states.maxByOrNull { it.createdAt } ?: throw IllegalStateException("Unknown schedule state")

    fun reserved(): Schedule  = apply { addState(State.RESERVED) }
    fun received(): Schedule = apply { addState(State.RECEIVED) }
    fun answered(): Schedule = apply { addState(State.ANSWERED) }
    fun canceled(): Schedule = apply { addState(State.CANCELLED) }
    fun mailError(): Schedule  = apply { addState(State.MAIL_ERROR) }

    fun isScheduled() = currentState.state == State.SCHEDULED
    fun isReserved() = currentState.state == State.RESERVED
    fun isReceived() = currentState.state == State.RECEIVED
    fun isAnswered() = currentState.state == State.ANSWERED
    fun isCanceled() = currentState.state == State.CANCELLED
    fun isMailError() = currentState.state == State.MAIL_ERROR

    fun canBeReserved() = states.none { it.state == State.RESERVED }

    private fun addState(state: State): Schedule =
        apply { states.add(ScheduleState(state = state)) }
}


