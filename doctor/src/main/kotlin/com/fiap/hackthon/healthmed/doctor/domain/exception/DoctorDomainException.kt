package com.fiap.hackthon.healthmed.doctor.domain.exception

class DoctorNotFoundException(key: String) : RuntimeException("Doctor $key not found")
