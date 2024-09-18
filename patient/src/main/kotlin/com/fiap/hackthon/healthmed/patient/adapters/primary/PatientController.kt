package com.fiap.hackthon.healthmed.patient.adapters.primary

import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import com.fiap.hackthon.healthmed.patient.ports.PatientCreationPort
import com.fiap.hackthon.healthmed.patient.ports.PatientDeletionPort
import com.fiap.hackthon.healthmed.patient.ports.PatientReadingPort
import com.fiap.hackthon.healthmed.patient.ports.PatientUpdatePort
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/patients")
class PatientController(
    private val patientCreationPort: PatientCreationPort,
    private val patientReadingPort: PatientReadingPort,
    private val patientUpdatePort: PatientUpdatePort,
    private val patientDeletionPort: PatientDeletionPort,
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@Valid @RequestBody payload: PatientCreationRequest): PatientReadResponse {
        return patientCreationPort.doCreate(
            name = payload.name,
            cpf = payload.cpf,
            email = payload.email,
            password = payload.password,
        ).toResponseDTO()
    }

    @GetMapping
    fun readAll(): List<PatientReadResponse> =
        patientReadingPort
            .readAll()
            .map(Patient::toResponseDTO)

    @GetMapping("{id}")
    fun readById(@PathVariable id: UUID): PatientReadResponse =
        patientReadingPort
            .readOne(id)
            .toResponseDTO()

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("{id}")
    fun update(@Valid @RequestBody payload: PatientUpdateRequest, @PathVariable id: UUID): PatientReadResponse =
        patientUpdatePort
            .doUpdate(id, payload.name, payload.email)
            .toResponseDTO()

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    fun delete(@PathVariable id: UUID) {
        patientDeletionPort
            .doDelete(id)
    }

}