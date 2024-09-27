package com.fiap.hackthon.healthmed.schedule

import com.fiap.hackthon.healthmed.doctor.DoctorConfiguration
import com.fiap.hackthon.healthmed.patient.PatientConfiguration
import com.fiap.hackthon.healthmed.shared.SharedConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(
    basePackageClasses = [
        SharedConfiguration::class,
        DoctorConfiguration::class,
        PatientConfiguration::class,
    ],
)
class ScheduleConfiguration
