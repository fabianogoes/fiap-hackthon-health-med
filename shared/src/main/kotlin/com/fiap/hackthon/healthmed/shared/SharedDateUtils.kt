package com.fiap.hackthon.healthmed.shared

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.TimeZone

@Suppress("unused")
object SharedDateUtils {
    private const val BR_TIMEZONE_ID = "America/Sao_Paulo"

    private val defaultLocalDateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
    private val defaultLocalDateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    private val defaultLocalTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    fun getBrTimeZone(): TimeZone = TimeZone.getTimeZone(BR_TIMEZONE_ID)

    fun LocalDateTime.toStringFormatted(formatter: DateTimeFormatter = defaultLocalDateTimeFormatter): String = formatter.format(this)

    fun LocalDate.toStringFormatted(formatter: DateTimeFormatter = defaultLocalDateFormatter): String = formatter.format(this)

    fun LocalTime.toStringFormatted(formatter: DateTimeFormatter = defaultLocalTimeFormatter): String = formatter.format(this)

    fun String.toLocalDateTime(formatter: DateTimeFormatter = defaultLocalDateTimeFormatter): LocalDateTime =
        LocalDateTime.parse(this, formatter)

    fun String.toLocalDate(formatter: DateTimeFormatter = defaultLocalDateFormatter): LocalDate = LocalDate.parse(this, formatter)

    fun String.toLocalTime(formatter: DateTimeFormatter = defaultLocalTimeFormatter): LocalDate = LocalDate.parse(this, formatter)
}
