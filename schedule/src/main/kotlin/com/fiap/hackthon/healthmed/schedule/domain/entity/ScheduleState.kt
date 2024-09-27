package com.fiap.hackthon.healthmed.schedule.domain.entity

import java.time.LocalDateTime
import java.util.UUID

data class ScheduleState(
    val id: UUID = UUID.randomUUID(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val state: State = State.SCHEDULED,
) {
    enum class State {
        SCHEDULED,
        RESERVED,
        RECEIVED,
        ANSWERED,
        CANCELLED,
        MAIL_ERROR,
    }
}
