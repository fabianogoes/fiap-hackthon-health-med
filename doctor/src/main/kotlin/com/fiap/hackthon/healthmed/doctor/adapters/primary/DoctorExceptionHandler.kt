package com.fiap.hackthon.healthmed.doctor.adapters.primary

import com.fiap.hackthon.healthmed.doctor.domain.exception.DoctorEmailAlreadyExistsException
import com.fiap.hackthon.healthmed.doctor.domain.exception.DoctorNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class DoctorExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DoctorNotFoundException::class)
    fun doctorNotFoundException(ex: DoctorNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse =
            ErrorResponse(
                status = HttpStatus.NOT_FOUND.value(),
                message = ex.message ?: "Doctor not found",
            )

        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DoctorEmailAlreadyExistsException::class)
    fun doctorAlreadyExistsException(ex: DoctorEmailAlreadyExistsException): ResponseEntity<ErrorResponse> {
        val errorResponse =
            ErrorResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = ex.message ?: "Doctor already exists",
            )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    data class ErrorResponse(
        val status: Int,
        val message: String,
    )
}
