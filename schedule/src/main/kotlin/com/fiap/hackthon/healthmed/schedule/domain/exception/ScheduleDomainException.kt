package com.fiap.hackthon.healthmed.schedule.domain.exception

import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.schedule.domain.entity.Slot

class ScheduleAlreadyExistsException(slot: Slot) :
    RuntimeException("Schedule with $slot, it is already exists")

class ScheduleAlreadyReservedException(schedule: Schedule) :
    RuntimeException("Schedule with ${schedule.slot}, it is already reserved")

class ScheduleStateCanNotBeCanceledException(schedule: Schedule) :
    RuntimeException("Schedule with state ${schedule.currentState.state}, can't be canceled")

class ScheduleNotFoundException(key: String) :
    RuntimeException("Schedule $key not found")
