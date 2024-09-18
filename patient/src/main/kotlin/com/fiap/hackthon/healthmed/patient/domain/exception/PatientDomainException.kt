package com.fiap.hackthon.healthmed.patient.domain.exception

import java.util.UUID

class PatientNotFoundException(id: UUID) : RuntimeException("Patient with id $id not found")