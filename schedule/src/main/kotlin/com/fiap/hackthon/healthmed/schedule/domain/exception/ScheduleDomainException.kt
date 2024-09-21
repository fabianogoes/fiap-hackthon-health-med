package com.fiap.hackthon.healthmed.schedule.domain.exception

import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule

class ScheduleAlreadyReservedException(schedule: Schedule) :
    RuntimeException("Schedule with ${schedule.slot}, it is already reserved")

class ScheduleNotFoundException(key: String) : RuntimeException("Schedule $key not found")