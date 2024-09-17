package com.fiap.hackthon.healthmed.doctor.primary.api

import com.fiap.hackthon.healthmed.doctor.domain.ports.DoctorReadingPort
import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import com.fiap.hackthon.healthmed.doctor.domain.ports.DoctorCreationPort
import com.fiap.hackthon.healthmed.doctor.domain.ports.DoctorDeletionPort
import com.fiap.hackthon.healthmed.doctor.domain.ports.DoctorUpdatePort
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import java.util.UUID

@RestController
@RequestMapping("/doctors")
class DoctorController(
    private val doctorCreationPort: DoctorCreationPort,
    private val doctorReadingPort: DoctorReadingPort,
    private val doctorUpdatePort: DoctorUpdatePort,
    private val doctorDeletionPort: DoctorDeletionPort,
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@Valid @RequestBody payload: DoctorCreationRequest): DoctorReadResponse {
        return doctorCreationPort.doCreate(
            name = payload.name,
            cpf = payload.cpf,
            email = payload.email,
            crm = payload.crm,
            password = payload.password,
        ).toResponseDTO()
    }

    @GetMapping
    fun readAll(): List<DoctorReadResponse> =
        doctorReadingPort
            .readAll()
            .map(Doctor::toResponseDTO)

    @GetMapping("{id}")
    fun readById(@PathVariable id: UUID): DoctorReadResponse =
        doctorReadingPort
            .readOne(id)
            .toResponseDTO()

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("{id}")
    fun update(@Valid @RequestBody payload: DoctorUpdateRequest, @PathVariable id: UUID): DoctorReadResponse =
        doctorUpdatePort
            .doUpdate(id, payload.name, payload.email)
            .toResponseDTO()

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    fun delete(@PathVariable id: UUID) {
        doctorDeletionPort
            .doDelete(id)
    }
}