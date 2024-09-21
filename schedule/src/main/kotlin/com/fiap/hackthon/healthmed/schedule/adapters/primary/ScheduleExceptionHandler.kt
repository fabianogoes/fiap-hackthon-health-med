package com.fiap.hackthon.healthmed.schedule.adapters.primary

import com.fiap.hackthon.healthmed.schedule.domain.exception.ScheduleAlreadyReservedException
import com.fiap.hackthon.healthmed.schedule.domain.exception.ScheduleNotFoundException
import com.fiap.hackthon.healthmed.shared.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ScheduleExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ScheduleAlreadyReservedException::class)
    fun scheduleAlreadyReservedException(ex: ScheduleAlreadyReservedException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = ex.message ?: "Schedule error",
        )

        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ScheduleNotFoundException::class)
    fun scheduleNotFoundException(ex: ScheduleNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            message = ex.message ?: "Schedule not found",
        )

        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

}