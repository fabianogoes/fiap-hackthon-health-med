package com.fiap.hackthon.healthmed.patient.domain.usecasecom.fiap.hackthon.healthmed.patient.domain.usecase

import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import com.fiap.hackthon.healthmed.patient.domain.exception.PatientNotFoundException
import com.fiap.hackthon.healthmed.patient.domain.usecase.PatientReadingUseCase
import com.fiap.hackthon.healthmed.patient.ports.PatientPersistencePort
import com.fiap.hackthon.healthmed.patient.ports.PatientReadingPort
import com.fiap.hackthon.healthmed.shared.email
import com.fiap.hackthon.healthmed.shared.faker
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class PatientReadingUseCaseTest {
    private val patientPersistencePort: PatientPersistencePort = mockk()
    private val patientReadingPort: PatientReadingPort = PatientReadingUseCase(patientPersistencePort)

    @Test
    fun `it should read a patient by email`() {
        // Given
        val email = faker.email()
        val expectedPatient = mockk<Patient>()
        every { patientPersistencePort.readOneByEmail(email) } returns expectedPatient

        // When
        val actualPatient = patientReadingPort.readOneByEmail(email)

        // Then
        assertThat(actualPatient).isNotNull()
        assertThat(actualPatient).isEqualTo(expectedPatient)
        verify(exactly = 1) { patientPersistencePort.readOneByEmail(email) }
    }

    @Test
    fun `it should not read a patient by email when not exists`() {
        // Given
        val email = faker.email()
        every { patientPersistencePort.readOneByEmail(email) } returns null

        // When
        assertThrows<PatientNotFoundException> { patientReadingPort.readOneByEmail(email) }

        // Then
        verify(exactly = 1) { patientPersistencePort.readOneByEmail(email) }
    }

    @Test
    fun `it should read a patient by id`() {
        // Given
        val id = UUID.randomUUID()
        val expectedPatient = mockk<Patient>()
        every { patientPersistencePort.readOneById(id) } returns expectedPatient

        // When
        val actualPatient = patientReadingPort.readOne(id)

        // Then
        assertThat(actualPatient).isNotNull()
        assertThat(actualPatient).isEqualTo(expectedPatient)
        verify(exactly = 1) { patientPersistencePort.readOneById(id) }
    }

    @Test
    fun `it should not read a patient by id when not exists`() {
        // Given
        val id = UUID.randomUUID()
        every { patientPersistencePort.readOneById(id) } returns null

        // When
        assertThrows<PatientNotFoundException> { patientReadingPort.readOne(id) }

        // Then
        verify(exactly = 1) { patientPersistencePort.readOneById(id) }
    }

    @Test
    fun `it should read all patients`() {
        // Given
        val expectedPatients = mockk<List<Patient>>()
        every { patientPersistencePort.readAll() } returns expectedPatients

        // When
        val actualPatients = patientReadingPort.readAll()

        // Then
        assertThat(actualPatients).isNotNull()
        assertThat(actualPatients).isEqualTo(expectedPatients)
        verify(exactly = 1) { patientPersistencePort.readAll() }
    }
}