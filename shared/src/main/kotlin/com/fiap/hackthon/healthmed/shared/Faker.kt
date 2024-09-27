package com.fiap.hackthon.healthmed.shared

import io.github.serpro69.kfaker.Faker
import kotlin.random.Random

val faker: Faker = Faker()

fun Faker.email(): Email = "${faker.name.firstName().lowercase()}@gmail.com".toEmailVO()

fun Faker.cpf(): CPF = randomCPF().toCpfVO()

fun Faker.crm(): String = randomCRM()

fun Faker.password(size: Int = 6): String = randomPassword(size)

fun randomCPF(): String {
    val n1: Int = randomizer()
    val n2: Int = randomizer()
    val n3: Int = randomizer()
    val n4: Int = randomizer()
    val n5: Int = randomizer()
    val n6: Int = randomizer()
    val n7: Int = randomizer()
    val n8: Int = randomizer()
    val n9: Int = randomizer()

    var d1 = (n9 * 2) + (n8 * 3) + (n7 * 4) + (n6 * 5) + (n5 * 6) + (n4 * 7) + (n3 * 8) + (n2 * 9) + (n1 * 10)
    d1 = 11 - d1.rem(11)
    if (d1 >= 10) d1 = 0

    var d2 = (d1 * 2) + (n9 * 3) + (n8 * 4) + (n7 * 5) + (n6 * 6) + (n5 * 7) + (n4 * 8) + (n3 * 9) + (n2 * 10) + (n1 * 11)
    d2 = 11 - d2.rem(11)
    if (d2 >= 10) d2 = 0

    return "$n1$n2$n3$n4$n5$n6$n7$n8$n9$d1$d2"
}

private fun randomizer(number: Int = 9) = (Math.random() * number).toInt()

private fun randomCRM(): String = "${randomizer(99999).leftPadWithZero(5)}/${randomUF().name}"

private fun randomPassword(size: Int = 6): String {
    val caracteres = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    return buildString {
        repeat(size) {
            append(caracteres.random())
        }
    }
}

private fun randomUF(): UF {
    val values = UF.entries
    val randomIndex = Random.nextInt(values.size)
    return values[randomIndex]
}

enum class UF {
    AC,
    AL,
    AP,
    AM,
    BA,
    CE,
    ES,
    GO,
    MA,
    MT,
    MS,
    MG,
    PA,
    PB,
    PR,
    PE,
    PI,
    RJ,
    RN,
    RS,
    RO,
    RR,
    SC,
    SP,
    SE,
    TO,
}
