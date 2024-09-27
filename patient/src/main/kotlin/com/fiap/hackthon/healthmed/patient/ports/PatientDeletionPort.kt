package com.fiap.hackthon.healthmed.patient.ports

import java.util.UUID

interface PatientDeletionPort {
    fun doDelete(id: UUID)
}
