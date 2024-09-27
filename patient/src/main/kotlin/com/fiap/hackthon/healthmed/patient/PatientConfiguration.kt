package com.fiap.hackthon.healthmed.patient

import com.fiap.hackthon.healthmed.user.UserConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackageClasses = [UserConfiguration::class])
class PatientConfiguration
