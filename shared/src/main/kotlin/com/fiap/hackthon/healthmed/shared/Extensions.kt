package com.fiap.hackthon.healthmed.shared

fun Int.leftPadWith(
    length: Int,
    padChar: Char = ' ',
): String = this.toString().padStart(length, padChar)

fun Int.leftPadWithZero(length: Int): String = this.leftPadWith(length, '0')
