package com.fiap.hackthon.healthmed.doctor.domain.exception

class DoctorNotFoundException(key: String) : RuntimeException("Doctor $key not found")

class DoctorEmailAlreadyExistsException(email: String) : RuntimeException("Doctor already exists for email: $email")
