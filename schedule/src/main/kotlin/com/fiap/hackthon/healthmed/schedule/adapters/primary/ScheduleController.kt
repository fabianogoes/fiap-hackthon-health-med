package com.fiap.hackthon.healthmed.schedule.adapters.primary

import com.fiap.hackthon.healthmed.schedule.ports.ScheduleCancellationPort
import com.fiap.hackthon.healthmed.schedule.ports.ScheduleCreationPort
import com.fiap.hackthon.healthmed.schedule.ports.ScheduleReadingPort
import com.fiap.hackthon.healthmed.schedule.ports.ScheduleReservationPort
import com.fiap.hackthon.healthmed.shared.Email
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.DeleteMapping
import java.util.UUID

@RestController
@RequestMapping("/api/schedules")
class ScheduleController(
    private val scheduleCreationPort: ScheduleCreationPort,
    private val scheduleReadingPort: ScheduleReadingPort,
    private val scheduleCancellationPort: ScheduleCancellationPort,
    private val scheduleReservationPort: ScheduleReservationPort,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody payload: ScheduleCreationRequest): ScheduleResponse =
        scheduleCreationPort.create(
            Email(payload.doctorEmail),
            payload.date,
            payload.startTime,
            payload.endTime,
        ).toDTO()

    @GetMapping
    fun readAll(): List<ScheduleResponse> =
        scheduleReadingPort
            .readAll()
            .map { it.toDTO() }

    @GetMapping("/doctor/{doctorEmail}")
    fun readAllByDoctor(@PathVariable doctorEmail: String): List<ScheduleResponse> =
        scheduleReadingPort
            .readAllByDoctor(Email(doctorEmail))
            .map { it.toDTO() }

    @GetMapping("/patient/{patientEmail}")
    fun readAllByPatient(@PathVariable patientEmail: String): List<ScheduleResponse> =
        scheduleReadingPort
            .readAllByPatient(Email(patientEmail))
            .map { it.toDTO() }

    @GetMapping("/doctor/{doctorEmail}/available")
    fun readAllAvailable(@PathVariable doctorEmail: String): List<ScheduleResponse> =
        scheduleReadingPort
            .readAllAvailableByDoctor(Email(doctorEmail))
            .map { it.toDTO() }

    @GetMapping("/doctor/{doctorEmail}/reserved")
    fun readAllReservedByDoctor(@PathVariable doctorEmail: String): List<ScheduleResponse> =
        scheduleReadingPort
            .readAllReservedByDoctor(Email(doctorEmail))
            .map { it.toDTO() }

    @GetMapping("/{id}")
    fun readOneById(@PathVariable id: UUID): ScheduleResponse =
        scheduleReadingPort
            .readById(id)
            .toDTO()

    @PostMapping("/{id}/reservation/patient/{patientEmail}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun reservation(@PathVariable id: UUID, @PathVariable patientEmail: String) {
        scheduleReservationPort.reserve(id, Email(patientEmail))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun cancellationById(@PathVariable id: UUID) {
        scheduleCancellationPort
            .cancelById(id)
    }

}



