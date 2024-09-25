package com.fiap.hackthon.healthmed.schedule.domain.usecase

import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import com.fiap.hackthon.healthmed.doctor.ports.DoctorPersistencePort
import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.schedule.ports.ScheduleCreationPort
import com.fiap.hackthon.healthmed.schedule.ports.SchedulePersistencePort
import com.fiap.hackthon.healthmed.shared.email
import com.fiap.hackthon.healthmed.shared.faker
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime

class ScheduleCreationUseCaseTest {

    private val schedulePersistencePort: SchedulePersistencePort = mockk()
    private val doctorPersistencePort: DoctorPersistencePort = mockk()
    private val scheduleCreationPort: ScheduleCreationPort = ScheduleCreationUseCase(
        schedulePersistencePort,
        doctorPersistencePort,
    )

    @Test
    fun `it should create a new test schedule`() {
        // Given
        val doctorEmail = faker.email()
        val date = LocalDate.now()
        val startTime = LocalTime.of(10, 0, 0)
        val endTime = startTime.plusHours(1)

        val expectedSchedule = mockk<Schedule>()
        every { doctorPersistencePort.readOneByEmail(any()) } returns mockk<Doctor>()
        every { schedulePersistencePort.save(any()) } returns expectedSchedule
        every { schedulePersistencePort.existsBySlot(any()) } returns false

        // When
        val actualSchedule = scheduleCreationPort.create(doctorEmail, date, startTime, endTime)

        // Then
        verify(exactly = 1) { doctorPersistencePort.readOneByEmail(any()) }
        verify(exactly = 1) { schedulePersistencePort.save(any()) }
        verify(exactly = 1) { schedulePersistencePort.existsBySlot(any()) }
        assertThat(actualSchedule).isNotNull()
        assertThat(actualSchedule).isEqualTo(expectedSchedule)
    }

}