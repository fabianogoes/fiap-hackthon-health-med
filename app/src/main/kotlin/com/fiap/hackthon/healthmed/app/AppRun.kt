package com.fiap.hackthon.healthmed.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableHealthMed
@SpringBootApplication
class AppRun

fun main(args: Array<String>) {
	runApplication<AppRun>(*args)
}
