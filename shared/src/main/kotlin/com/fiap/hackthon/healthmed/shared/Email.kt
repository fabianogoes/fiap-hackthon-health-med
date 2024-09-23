package com.fiap.hackthon.healthmed.shared

data class Email (
    val value: String,
)

fun String.toEmailVO(): Email = Email(this)