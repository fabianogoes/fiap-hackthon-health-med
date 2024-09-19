package com.fiap.hackthon.healthmed.schedule.domain.entity

import com.fiap.hackthon.healthmed.schedule.domain.entity.ScheduleState.State
import com.fiap.hackthon.healthmed.shared.Email
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class ScheduleTest {

    @Test
    fun `it should validate change states`() {
        val schedule = Schedule(
            id = UUID.randomUUID(),
            date = LocalDate.now(),
            startTime = LocalTime.of(10, 0),
            endTime = LocalTime.of(11, 0),
            doctorEmail = Email("doctorwho@test.com")
        )

        assertThat(schedule.currentState.state).isEqualTo(State.SCHEDULED)
        assertThat(schedule.isScheduled()).isTrue()

        assertThat(schedule.reserved().currentState.state).isEqualTo(State.RESERVED)
        assertThat(schedule.isReserved()).isTrue()

        assertThat(schedule.received().currentState.state).isEqualTo(State.RECEIVED)
        assertThat(schedule.isReceived()).isTrue()

        assertThat(schedule.answered().currentState.state).isEqualTo(State.ANSWERED)
        assertThat(schedule.isAnswered()).isTrue()

        assertThat(schedule.canceled().currentState.state).isEqualTo(State.CANCELLED)
        assertThat(schedule.isCanceled()).isTrue()
    }

}