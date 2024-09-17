package com.fiap.hackthon.healthmed.doctor.domain.exception

import java.util.UUID

class DoctorNotFoundException(id: UUID) : RuntimeException("Doctor with id $id not found")