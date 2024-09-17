package com.fiap.hackthon.healthmed.doctor.domain.ports

import java.util.UUID

interface DoctorDeletionPort {
    fun doDelete(id: UUID)
}