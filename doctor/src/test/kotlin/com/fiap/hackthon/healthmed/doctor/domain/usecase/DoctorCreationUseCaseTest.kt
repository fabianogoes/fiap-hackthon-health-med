package com.fiap.hackthon.healthmed.doctor.domain.usecase

import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import com.fiap.hackthon.healthmed.doctor.domain.entity.toCrmVO
import com.fiap.hackthon.healthmed.doctor.domain.exception.DoctorEmailAlreadyExistsException
import com.fiap.hackthon.healthmed.doctor.ports.DoctorCreationPort
import com.fiap.hackthon.healthmed.doctor.ports.DoctorPersistencePort
import com.fiap.hackthon.healthmed.shared.faker
import com.fiap.hackthon.healthmed.shared.email
import com.fiap.hackthon.healthmed.shared.cpf
import com.fiap.hackthon.healthmed.shared.crm
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

class DoctorCreationUseCaseTest {

    private val doctorPersistencePort: DoctorPersistencePort = mockk()
    private val userCreateUserPort: CreateUserPort = mockk()

    private val doctorCreationPort: DoctorCreationPort = DoctorCreationUseCase(
        doctorPersistencePort,
        userCreateUserPort,
    )

    @Test
    fun `it should create a new Doctor`() {
        // Given
        val name = faker.name.firstName()
        val email = faker.email()
        val cpf = faker.cpf()
        val crm = faker.crm().toCrmVO()
        val password = faker.password()
        val userRole = User.Role.DOCTOR_ROLE

        val user = User(email, password, userRole)
        val expectedDoctor = Doctor(UUID.randomUUID(), name, cpf, email, crm, user)
        every { userCreateUserPort.create(email.value, password, userRole.name) } returns user
        every { doctorPersistencePort.create(any()) } returns expectedDoctor
        every { doctorPersistencePort.readOneByEmail(email) } returns null

        // When
        val actualDoctor = doctorCreationPort
            .doCreate(name, cpf.number, email.value, crm.number, password)

        // Then
        verify(exactly = 1) { doctorPersistencePort.create(any()) }
        verify(exactly = 1) { doctorPersistencePort.readOneByEmail(email) }
        verify(exactly = 1) { userCreateUserPort.create(email.value, password, userRole.name) }

        assertThat(actualDoctor.name).isEqualTo(name)
        assertThat(actualDoctor.email).isEqualTo(email)
        assertThat(actualDoctor.cpf).isEqualTo(cpf)
        assertThat(actualDoctor.crm).isEqualTo(crm)
        assertThat(actualDoctor.user).isNotNull()
        assertThat(actualDoctor.user.email).isEqualTo(email)
        assertThat(actualDoctor.user.role).isEqualTo(userRole)
    }

    @Test
    fun `it should not create a new Doctor when email already exists`() {
        // Given
        val name = faker.name.firstName()
        val email = faker.email()
        val cpf = faker.cpf()
        val crm = faker.crm().toCrmVO()
        val password = faker.password()

        val doctorAlreadyExists = mockk<Doctor>()
        every { doctorPersistencePort.readOneByEmail(email) } returns doctorAlreadyExists

        // When
        assertThrows<DoctorEmailAlreadyExistsException> {
            doctorCreationPort.doCreate(name, cpf.number, email.value, crm.number, password)
        }

        // Then
        verify(exactly = 1) { doctorPersistencePort.readOneByEmail(email) }
    }

}