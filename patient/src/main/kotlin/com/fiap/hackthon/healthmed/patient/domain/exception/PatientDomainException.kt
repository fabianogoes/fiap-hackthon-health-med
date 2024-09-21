package com.fiap.hackthon.healthmed.patient.domain.exception

class PatientNotFoundException(key: String) : RuntimeException("Patient $key not found")