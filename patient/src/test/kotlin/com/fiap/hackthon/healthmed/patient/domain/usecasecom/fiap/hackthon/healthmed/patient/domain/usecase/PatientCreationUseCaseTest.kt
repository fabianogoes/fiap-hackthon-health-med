package com.fiap.hackthon.healthmed.patient.domain.usecasecom.fiap.hackthon.healthmed.patient.domain.usecase

import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import com.fiap.hackthon.healthmed.patient.domain.exception.PatientEmailAlreadyExistsException
import com.fiap.hackthon.healthmed.patient.domain.usecase.PatientCreationUseCase
import com.fiap.hackthon.healthmed.patient.ports.PatientCreationPort
import com.fiap.hackthon.healthmed.patient.ports.PatientPersistencePort
import com.fiap.hackthon.healthmed.shared.faker
import com.fiap.hackthon.healthmed.shared.email
import com.fiap.hackthon.healthmed.shared.cpf
import com.fiap.hackthon.healthmed.shared.password
import com.fiap.hackthon.healthmed.user.domain.entity.User
import com.fiap.hackthon.healthmed.user.ports.CreateUserPort
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class PatientCreationUseCaseTest {

    private val patientPersistencePort: PatientPersistencePort = mockk()
    private val userCreateUserPort: CreateUserPort = mockk()

    private val patientCreationPort: PatientCreationPort = PatientCreationUseCase(
        patientPersistencePort,
        userCreateUserPort,
    )

    @Test
    fun `it should create a new Patient`() {
        // Given
        val name = faker.name.firstName()
        val email = faker.email()
        val cpf = faker.cpf()
        val password = faker.password()
        val userRole = User.Role.PATIENT_ROLE

        val user = User(email, password, userRole)
        val expectedPatient = Patient(UUID.randomUUID(), name, cpf, email, user)
        every { userCreateUserPort.create(email.value, password, userRole.name) } returns user
        every { patientPersistencePort.create(any()) } returns expectedPatient
        every { patientPersistencePort.readOneByEmail(email) } returns null

        // When
        val actualPatient = patientCreationPort
            .doCreate(name, cpf.number, email.value, password)

        // Then
        verify(exactly = 1) { patientPersistencePort.create(any()) }
        verify(exactly = 1) { patientPersistencePort.readOneByEmail(email) }
        verify(exactly = 1) { userCreateUserPort.create(email.value, password, userRole.name) }

        assertThat(actualPatient.name).isEqualTo(name)
        assertThat(actualPatient.email).isEqualTo(email)
        assertThat(actualPatient.cpf).isEqualTo(cpf)
        assertThat(actualPatient.user).isNotNull()
        assertThat(actualPatient.user.email).isEqualTo(email)
        assertThat(actualPatient.user.role).isEqualTo(userRole)
    }

    @Test
    fun `it should not create a new Patient when email already exists`() {
        // Given
        val name = faker.name.firstName()
        val email = faker.email()
        val cpf = faker.cpf()
        val password = faker.password()

        val patientAlreadyExists = mockk<Patient>()
        every { patientPersistencePort.readOneByEmail(email) } returns patientAlreadyExists

        // When
        assertThrows<PatientEmailAlreadyExistsException> {
            patientCreationPort.doCreate(name, cpf.number, email.value, password)
        }

        // Then
        verify(exactly = 1) { patientPersistencePort.readOneByEmail(email) }
    }

}