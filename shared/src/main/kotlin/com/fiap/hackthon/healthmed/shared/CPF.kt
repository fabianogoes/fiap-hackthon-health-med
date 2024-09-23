package com.fiap.hackthon.healthmed.shared

data class CPF(
    val number: String
)

fun String.toCpfVO() = CPF(this)