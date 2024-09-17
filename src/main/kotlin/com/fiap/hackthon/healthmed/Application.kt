package com.fiap.hackthon.healthmed

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.sql.DataSource

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

@RestController
class HealthController(private val dataSource: DataSource) {

	@GetMapping("/db-info")
	fun getDatabaseInfo(): Map<String, String> {
		dataSource.connection.use { connection ->
			val metaData = connection.metaData
			return mapOf(
				"databaseProductName" to metaData.databaseProductName,
				"databaseProductVersion" to metaData.databaseProductVersion,
				"driverName" to metaData.driverName,
				"driverVersion" to metaData.driverVersion,
				"url" to metaData.url,
				"userName" to metaData.userName
			)
		}
	}

}