package com.fiap.hackthon.healthmed.app

import com.fiap.hackthon.healthmed.doctor.DoctorConfiguration
import com.fiap.hackthon.healthmed.patient.PatientConfiguration
import com.fiap.hackthon.healthmed.schedule.ScheduleConfiguration
import com.fiap.hackthon.healthmed.shared.SharedConfiguration
import com.fiap.hackthon.healthmed.shared.SharedDateUtils
import com.fiap.hackthon.healthmed.user.UserConfiguration
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import java.util.TimeZone


@Configuration
class AppConfiguration {

    @PostConstruct
    fun init() {
        TimeZone.setDefault(SharedDateUtils.getBrTimeZone())
    }
}

@ComponentScan(basePackageClasses = [
    AppRun::class,
    SharedConfiguration::class,
    DoctorConfiguration::class,
    PatientConfiguration::class,
    UserConfiguration::class,
    ScheduleConfiguration::class,
])
annotation class EnableHealthMed