package com.fiap.hackthon.healthmed.doctor.domain.usecase

import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import com.fiap.hackthon.healthmed.doctor.domain.exception.DoctorNotFoundException
import com.fiap.hackthon.healthmed.doctor.ports.DoctorPersistencePort
import com.fiap.hackthon.healthmed.doctor.ports.DoctorReadingPort
import com.fiap.hackthon.healthmed.shared.email
import com.fiap.hackthon.healthmed.shared.faker
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class DoctorReadingUseCaseTest {
    private val doctorPersistencePort: DoctorPersistencePort = mockk()
    private val doctorReadingPort: DoctorReadingPort = DoctorReadingUseCase(doctorPersistencePort)

    @Test
    fun `it should read a doctor by email`() {
        // Given
        val email = faker.email()
        val expectedDoctor = mockk<Doctor>()
        every { doctorPersistencePort.readOneByEmail(email) } returns expectedDoctor

        // When
        val actualDoctor = doctorReadingPort.readOneByEmail(email)

        // Then
        assertThat(actualDoctor).isNotNull()
        assertThat(actualDoctor).isEqualTo(expectedDoctor)
        verify(exactly = 1) { doctorPersistencePort.readOneByEmail(email) }
    }

    @Test
    fun `it should not read a doctor by email when not exists`() {
        // Given
        val email = faker.email()
        every { doctorPersistencePort.readOneByEmail(email) } returns null

        // When
        assertThrows<DoctorNotFoundException> { doctorReadingPort.readOneByEmail(email) }

        // Then
        verify(exactly = 1) { doctorPersistencePort.readOneByEmail(email) }
    }

    @Test
    fun `it should read a doctor by id`() {
        // Given
        val id = UUID.randomUUID()
        val expectedDoctor = mockk<Doctor>()
        every { doctorPersistencePort.readOneById(id) } returns expectedDoctor

        // When
        val actualDoctor = doctorReadingPort.readOne(id)

        // Then
        assertThat(actualDoctor).isNotNull()
        assertThat(actualDoctor).isEqualTo(expectedDoctor)
        verify(exactly = 1) { doctorPersistencePort.readOneById(id) }
    }

    @Test
    fun `it should not read a doctor by id when not exists`() {
        // Given
        val id = UUID.randomUUID()
        every { doctorPersistencePort.readOneById(id) } returns null

        // When
        assertThrows<DoctorNotFoundException> { doctorReadingPort.readOne(id) }

        // Then
        verify(exactly = 1) { doctorPersistencePort.readOneById(id) }
    }

    @Test
    fun `it should read all doctors`() {
        // Given
        val expectedDoctors = mockk<List<Doctor>>()
        every { doctorPersistencePort.readAll() } returns expectedDoctors

        // When
        val actualDoctors = doctorReadingPort.readAll()

        // Then
        assertThat(actualDoctors).isNotNull()
        assertThat(actualDoctors).isEqualTo(expectedDoctors)
        verify(exactly = 1) { doctorPersistencePort.readAll() }
    }
}
