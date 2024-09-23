package com.fiap.hackthon.healthmed.doctor.domain.entity

data class CRM(
    val number: String,
)

fun String.toCrmVO(): CRM = CRM(this)
