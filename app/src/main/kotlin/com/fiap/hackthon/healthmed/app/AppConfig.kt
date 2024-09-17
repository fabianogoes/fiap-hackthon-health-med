package com.fiap.hackthon.healthmed.app

import com.fiap.hackthon.healthmed.doctor.DoctorConfig
import com.fiap.hackthon.healthmed.patient.PatientConfig
import com.fiap.hackthon.healthmed.shared.SharedConfig
import com.fiap.hackthon.healthmed.shared.SharedDateUtils
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import java.util.TimeZone


@Configuration
class AppConfig {

    @PostConstruct
    fun init() {
        TimeZone.setDefault(SharedDateUtils.getBrTimeZone())
    }
}

@ComponentScan(basePackageClasses = [
    AppRun::class,
    SharedConfig::class,
    DoctorConfig::class,
    PatientConfig::class,
])
annotation class EnableHealthMed