package com.fiap.hackthon.healthmed.patient

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/patients")
class PatientController {

    @GetMapping
    fun home(): Map<String, String> = mapOf(
        "Domain Name" to "patients",
    )

}