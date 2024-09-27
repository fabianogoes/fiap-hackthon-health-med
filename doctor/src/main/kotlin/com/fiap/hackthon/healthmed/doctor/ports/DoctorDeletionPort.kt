package com.fiap.hackthon.healthmed.doctor.ports

import java.util.UUID

interface DoctorDeletionPort {
    fun doDelete(id: UUID)
}
