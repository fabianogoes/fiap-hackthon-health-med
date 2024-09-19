package com.fiap.hackthon.healthmed.schedule.ports

import java.util.UUID

interface ScheduleCancellationPort {
    fun cancelById(id: UUID)
}