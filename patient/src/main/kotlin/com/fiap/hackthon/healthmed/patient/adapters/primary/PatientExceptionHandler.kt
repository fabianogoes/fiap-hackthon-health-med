package com.fiap.hackthon.healthmed.patient.adapters.primary

import com.fiap.hackthon.healthmed.patient.domain.exception.PatientEmailAlreadyExistsException
import com.fiap.hackthon.healthmed.patient.domain.exception.PatientNotFoundException
import com.fiap.hackthon.healthmed.shared.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class PatientExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PatientNotFoundException::class)
    fun patientNotFoundException(ex: PatientNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse =
            ErrorResponse(
                status = HttpStatus.NOT_FOUND.value(),
                message = ex.message ?: "Patient not found",
            )

        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PatientEmailAlreadyExistsException::class)
    fun patientAlreadyExistsException(ex: PatientEmailAlreadyExistsException): ResponseEntity<ErrorResponse> {
        val errorResponse =
            ErrorResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = ex.message ?: "Patient already exists",
            )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}
