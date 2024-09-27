package com.fiap.hackthon.healthmed.schedule.domain.usecase

import com.fiap.hackthon.healthmed.doctor.domain.exception.DoctorNotFoundException
import com.fiap.hackthon.healthmed.doctor.domain.faker.createDoctor
import com.fiap.hackthon.healthmed.doctor.ports.DoctorPersistencePort
import com.fiap.hackthon.healthmed.patient.domain.exception.PatientNotFoundException
import com.fiap.hackthon.healthmed.patient.domain.faker.createPatient
import com.fiap.hackthon.healthmed.patient.ports.PatientPersistencePort
import com.fiap.hackthon.healthmed.schedule.domain.exception.ScheduleNotFoundException
import com.fiap.hackthon.healthmed.schedule.domain.faker.createSchedule
import com.fiap.hackthon.healthmed.schedule.ports.SchedulePersistencePort
import com.fiap.hackthon.healthmed.schedule.ports.ScheduleReservationPort
import com.fiap.hackthon.healthmed.shared.ports.MailPort
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ScheduleReservationUseCaseTest {
    private val schedulePersistencePort: SchedulePersistencePort = mockk()
    private val doctorPersistencePort: DoctorPersistencePort = mockk()
    private val patientPersistencePort: PatientPersistencePort = mockk()
    private val mailPort: MailPort = mockk()

    private val scheduleReservationPort: ScheduleReservationPort =
        ScheduleReservationUseCase(
            schedulePersistencePort,
            doctorPersistencePort,
            patientPersistencePort,
            mailPort,
        )

    @Test
    fun `it should reserve successfully`() {
        // Given
        val expectedDoctor = createDoctor()
        val expectedPatient = createPatient()
        val expectedSchedule =
            createSchedule(
                doctorEmail = expectedDoctor.email,
                patientEmail = expectedPatient.email,
            )
        every { schedulePersistencePort.readById(expectedSchedule.id) } returns expectedSchedule
        every { doctorPersistencePort.readOneByEmail(expectedDoctor.email) } returns expectedDoctor
        every { patientPersistencePort.readOneByEmail(expectedPatient.email) } returns expectedPatient
        every { schedulePersistencePort.save(any()) } returns expectedSchedule
        every { mailPort.sendEmail(any(), any(), any(), any()) } returns mockk<Unit>()

        // When
        scheduleReservationPort.reserve(expectedSchedule.id, expectedPatient.email)

        // Then
        verify(exactly = 1) { schedulePersistencePort.readById(expectedSchedule.id) }
        verify(exactly = 1) { doctorPersistencePort.readOneByEmail(expectedDoctor.email) }
        verify(exactly = 1) { patientPersistencePort.readOneByEmail(expectedPatient.email) }
        verify(exactly = 1) { schedulePersistencePort.save(any()) }
        verify(exactly = 1) { mailPort.sendEmail(any(), any(), any(), any()) }
    }

    @Test
    fun `it should throw ScheduleNotFoundException when schedule does not exists`() {
        // Given
        val expectedDoctor = createDoctor()
        val expectedPatient = createPatient()
        val expectedSchedule =
            createSchedule(
                doctorEmail = expectedDoctor.email,
                patientEmail = expectedPatient.email,
            )
        every { schedulePersistencePort.readById(expectedSchedule.id) } returns null

        // When
        assertThrows<ScheduleNotFoundException> { scheduleReservationPort.reserve(expectedSchedule.id, expectedPatient.email) }

        // Then
        verify(exactly = 1) { schedulePersistencePort.readById(expectedSchedule.id) }
    }

    @Test
    fun `it should throw DoctorNotFoundException when doctor does not exists`() {
        // Given
        val expectedDoctor = createDoctor()
        val expectedPatient = createPatient()
        val expectedSchedule =
            createSchedule(
                doctorEmail = expectedDoctor.email,
                patientEmail = expectedPatient.email,
            )
        every { schedulePersistencePort.readById(expectedSchedule.id) } returns expectedSchedule
        every { doctorPersistencePort.readOneByEmail(expectedDoctor.email) } returns null

        // When
        assertThrows<DoctorNotFoundException> { scheduleReservationPort.reserve(expectedSchedule.id, expectedPatient.email) }

        // Then
        verify(exactly = 1) { schedulePersistencePort.readById(expectedSchedule.id) }
        verify(exactly = 1) { doctorPersistencePort.readOneByEmail(expectedDoctor.email) }
    }

    @Test
    fun `it should throw PatientNotFoundException when patient does not exists`() {
        // Given
        val expectedDoctor = createDoctor()
        val expectedPatient = createPatient()
        val expectedSchedule =
            createSchedule(
                doctorEmail = expectedDoctor.email,
                patientEmail = expectedPatient.email,
            )
        every { schedulePersistencePort.readById(expectedSchedule.id) } returns expectedSchedule
        every { doctorPersistencePort.readOneByEmail(expectedDoctor.email) } returns expectedDoctor
        every { patientPersistencePort.readOneByEmail(expectedPatient.email) } returns null

        // When
        assertThrows<PatientNotFoundException> { scheduleReservationPort.reserve(expectedSchedule.id, expectedPatient.email) }

        // Then
        verify(exactly = 1) { schedulePersistencePort.readById(expectedSchedule.id) }
        verify(exactly = 1) { doctorPersistencePort.readOneByEmail(expectedDoctor.email) }
        verify(exactly = 1) { patientPersistencePort.readOneByEmail(expectedPatient.email) }
    }

    @Test
    fun `it should reserve error when send mail failed`() {
        // Given
        val expectedDoctor = createDoctor()
        val expectedPatient = createPatient()
        val expectedSchedule =
            createSchedule(
                doctorEmail = expectedDoctor.email,
                patientEmail = expectedPatient.email,
            )
        every { schedulePersistencePort.readById(expectedSchedule.id) } returns expectedSchedule
        every { doctorPersistencePort.readOneByEmail(expectedDoctor.email) } returns expectedDoctor
        every { patientPersistencePort.readOneByEmail(expectedPatient.email) } returns expectedPatient
        every { schedulePersistencePort.save(any()) } returns expectedSchedule

        // When
        scheduleReservationPort.reserve(expectedSchedule.id, expectedPatient.email)

        // Then
        verify(exactly = 1) { schedulePersistencePort.readById(expectedSchedule.id) }
        verify(exactly = 1) { doctorPersistencePort.readOneByEmail(expectedDoctor.email) }
        verify(exactly = 1) { patientPersistencePort.readOneByEmail(expectedPatient.email) }
        verify(exactly = 2) { schedulePersistencePort.save(any()) }
        verify(exactly = 1) { mailPort.sendEmail(any(), any(), any(), any()) }
    }
}
