package com.fiap.hackthon.healthmed.schedule.adapters.primary

import com.fiap.hackthon.healthmed.schedule.domain.exception.ScheduleAlreadyExistsException
import com.fiap.hackthon.healthmed.schedule.domain.exception.ScheduleAlreadyReservedException
import com.fiap.hackthon.healthmed.schedule.domain.exception.ScheduleNotFoundException
import com.fiap.hackthon.healthmed.schedule.domain.exception.ScheduleStateCanNotBeCanceledException
import com.fiap.hackthon.healthmed.shared.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ScheduleExceptionHandler {

    @ExceptionHandler(ScheduleAlreadyReservedException::class)
    fun scheduleAlreadyReservedException(ex: ScheduleAlreadyReservedException) =
        ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = ex.message ?: SCHEDULE_ERROR_MESSAGE,
        ).let { ResponseEntity.badRequest().body(it) }

    @ExceptionHandler(ScheduleStateCanNotBeCanceledException::class)
    fun scheduleStateCanNotBeCanceledExceptionException(ex: ScheduleStateCanNotBeCanceledException) =
        ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = ex.message ?: SCHEDULE_ERROR_MESSAGE,
        ).let { ResponseEntity.badRequest().body(it) }

    @ExceptionHandler(ScheduleAlreadyExistsException::class)
    fun scheduleAlreadyExistsException(ex: ScheduleAlreadyExistsException) =
        ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = ex.message ?: SCHEDULE_ERROR_MESSAGE
        ).let { ResponseEntity.badRequest().body(it) }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ScheduleNotFoundException::class)
    fun scheduleNotFoundException(ex: ScheduleNotFoundException) =
        ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            message = ex.message ?: SCHEDULE_NOT_FOUND,
        ).let { ResponseEntity(it, HttpStatus.NOT_FOUND) }

    companion object {
        private const val SCHEDULE_ERROR_MESSAGE = "Schedule error"
        private const val SCHEDULE_NOT_FOUND = "Schedule not found"
    }

}