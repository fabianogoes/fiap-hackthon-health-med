package com.fiap.hackthon.healthmed.schedule.domain.usecase

import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import com.fiap.hackthon.healthmed.doctor.ports.DoctorPersistencePort
import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import com.fiap.hackthon.healthmed.patient.ports.PatientPersistencePort
import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.schedule.domain.faker.createSchedule
import com.fiap.hackthon.healthmed.schedule.ports.SchedulePersistencePort
import com.fiap.hackthon.healthmed.schedule.ports.ScheduleReadingPort
import com.fiap.hackthon.healthmed.shared.email
import com.fiap.hackthon.healthmed.shared.faker
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScheduleReadingUseCaseTest {

    private val schedulePersistencePort: SchedulePersistencePort = mockk()
    private val doctorPersistencePort: DoctorPersistencePort = mockk()
    private val patientPersistencePort: PatientPersistencePort = mockk()

    private val scheduleReadingPort: ScheduleReadingPort = ScheduleReadingUseCase(
        schedulePersistencePort,
        doctorPersistencePort,
        patientPersistencePort,
    )

    @Test
    fun `it should read all schedules by doctor`() {
        // Given
        val doctorEmail = faker.email()

        val expectedSchedules = listOf(mockk<Schedule>())
        val expectedDoctor = mockk<Doctor>()
        every { doctorPersistencePort.readOneByEmail(doctorEmail) } returns expectedDoctor
        every { schedulePersistencePort.readAllByDoctor(doctorEmail) } returns expectedSchedules

        // When
        val actualSchedules = scheduleReadingPort.readAllByDoctor(doctorEmail)

        // Then
        verify(exactly = 1) { schedulePersistencePort.readAllByDoctor(doctorEmail) }
        verify(exactly = 1) { doctorPersistencePort.readOneByEmail(doctorEmail) }
        assertThat(actualSchedules).isNotNull()
        assertThat(actualSchedules).isEqualTo(expectedSchedules)
    }

    @Test
    fun `it should read all schedules available by doctor`() {
        // Given
        val doctorEmail = faker.email()
        val expectedDoctor = mockk<Doctor>()
        val expectedSchedules = listOf(createSchedule(doctorEmail))
        every { doctorPersistencePort.readOneByEmail(doctorEmail) } returns expectedDoctor
        every { schedulePersistencePort.readAllByDoctor(doctorEmail) } returns expectedSchedules

        // When
        val actualSchedules = scheduleReadingPort.readAllAvailableByDoctor(doctorEmail)

        // Then
        verify(exactly = 1) { schedulePersistencePort.readAllByDoctor(doctorEmail) }
        verify(exactly = 1) { doctorPersistencePort.readOneByEmail(doctorEmail) }
        assertThat(actualSchedules).isNotNull()
        assertThat(actualSchedules).isEqualTo(expectedSchedules)
    }

    @Test
    fun `it should read all schedules reserved by doctor`() {
        // Given
        val doctorEmail = faker.email()
        val patientEmail = faker.email()
        val expectedDoctor = mockk<Doctor>()
        val expectedSchedules = listOf(createSchedule(doctorEmail = doctorEmail, patientEmail = patientEmail).reserved())
        every { doctorPersistencePort.readOneByEmail(doctorEmail) } returns expectedDoctor
        every { schedulePersistencePort.readAllByDoctor(doctorEmail) } returns expectedSchedules

        // When
        val actualSchedules = scheduleReadingPort.readAllReservedByDoctor(doctorEmail)

        // Then
        verify(exactly = 1) { schedulePersistencePort.readAllByDoctor(doctorEmail) }
        verify(exactly = 1) { doctorPersistencePort.readOneByEmail(doctorEmail) }
        assertThat(actualSchedules).isNotNull()
        assertThat(actualSchedules).isEqualTo(expectedSchedules)
    }

    @Test
    fun `it should read all schedules by patient`() {
        // Given
        val patientEmail = faker.email()

        val expectedSchedules = listOf(mockk<Schedule>())
        val expectedPatient = mockk<Patient>()
        every { patientPersistencePort.readOneByEmail(patientEmail) } returns expectedPatient
        every { schedulePersistencePort.readAllByPatient(patientEmail) } returns expectedSchedules

        // When
        val actualSchedules = scheduleReadingPort.readAllByPatient(patientEmail)

        // Then
        verify(exactly = 1) { schedulePersistencePort.readAllByPatient(patientEmail) }
        verify(exactly = 1) { patientPersistencePort.readOneByEmail(patientEmail) }
        assertThat(actualSchedules).isNotNull()
        assertThat(actualSchedules).isEqualTo(expectedSchedules)
    }

}