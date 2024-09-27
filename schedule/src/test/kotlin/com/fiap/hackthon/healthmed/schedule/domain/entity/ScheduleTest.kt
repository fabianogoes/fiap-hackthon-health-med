package com.fiap.hackthon.healthmed.schedule.domain.entity

import com.fiap.hackthon.healthmed.schedule.domain.entity.ScheduleState.State
import com.fiap.hackthon.healthmed.shared.Email
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class ScheduleTest {
    private val schedule =
        Schedule(
            id = UUID.randomUUID(),
            slot =
                Slot(
                    doctorEmail = Email("doctorwho@test.com"),
                    date = LocalDate.now(),
                    startTime = LocalTime.of(10, 0),
                    endTime = LocalTime.of(11, 0),
                ),
        )

    @Order(1)
    @Test
    fun `it should validate if create a schedule with state SCHEDULED`() {
        assertThat(schedule.currentState.state).isEqualTo(State.SCHEDULED)
        assertThat(schedule.isScheduled()).isTrue()
    }

    @Test
    fun `it should validate if change state to RESERVED`() {
        schedule.reserved()
        assertThat(schedule.currentState.state).isEqualTo(State.RESERVED)
        assertThat(schedule.isReserved()).isTrue()
    }

    @Test
    fun `it should validate if change state to RECEIVED`() {
        schedule.received()
        assertThat(schedule.currentState.state).isEqualTo(State.RECEIVED)
        assertThat(schedule.isReceived()).isTrue()
    }

    @Test
    fun `it should validate if change state to ANSWERED`() {
        schedule.answered()
        assertThat(schedule.currentState.state).isEqualTo(State.ANSWERED)
        assertThat(schedule.isAnswered()).isTrue()
    }

    @Test
    fun `it should validate if change state to CANCELLED`() {
        schedule.canceled()
        assertThat(schedule.currentState.state).isEqualTo(State.CANCELLED)
        assertThat(schedule.isCanceled()).isTrue()
    }

    @Test
    fun `it should validate if change state to MAIL_ERROR`() {
        schedule.mailError()
        assertThat(schedule.currentState.state).isEqualTo(State.MAIL_ERROR)
        assertThat(schedule.isMailError()).isTrue()
    }
}
