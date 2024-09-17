package com.fiap.hackthon.healthmed.app

import com.fiap.hackthon.healthmed.shared.SharedConstants
import com.fiap.hackthon.healthmed.shared.SharedDateUtils.toStringFormatted
import org.springframework.boot.env.OriginTrackedMapPropertySource
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.TimeZone
import javax.sql.DataSource

@RestController
@RequestMapping("/")
class AppController(
    private val environment: ConfigurableEnvironment,
    private val dataSource: DataSource,
) {
    @GetMapping
    fun home() = properties()

    @GetMapping("/properties")
    fun properties(): Map<String, String?> {
        val defaultProperties = mutableMapOf<String, String?>()
            .plus("Application Description" to SharedConstants.APPLICATION_NAME)
            .plus("Default Time Zone" to TimeZone.getDefault().toZoneId().id)
            .plus("Local Date Time Now" to LocalDateTime.now().toStringFormatted())
            .plus("Local Date Now" to LocalDate.now().toStringFormatted())
            .plus("Local Time Now" to LocalTime.now().toStringFormatted())

        val systemProperties = environment.propertySources
            .filterIsInstance<OriginTrackedMapPropertySource>()
            .flatMap { it.source.keys }
            .associateWith {
                kotlin.runCatching {
                    environment.getProperty(it)
                }.getOrNull()
            }

        return mutableMapOf<String, String?>()
            .plus(defaultProperties)
            .plus(systemProperties)
    }

    @GetMapping("/db")
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