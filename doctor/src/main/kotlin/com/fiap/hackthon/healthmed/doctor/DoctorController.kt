package com.fiap.hackthon.healthmed.doctor

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/doctors")
class DoctorController {

    @GetMapping
    fun home(): Map<String, String> = mapOf(
        "Domain Name" to "Doctors",
    )

}