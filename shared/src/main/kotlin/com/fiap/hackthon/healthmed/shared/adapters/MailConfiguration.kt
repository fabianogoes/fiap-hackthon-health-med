package com.fiap.hackthon.healthmed.shared.adapters

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.Properties

@Configuration
class MailConfiguration(
    @Value("\${spring.mail.host}") private val host: String,
    @Value("\${spring.mail.port}") private val port: Int,
    @Value("\${spring.mail.username}") private val username: String,
    @Value("\${spring.mail.password}") private val password: String,
    @Value("\${spring.mail.properties.mail.smtp.auth}") private val smtpAuth: Boolean,
    @Value("\${spring.mail.properties.mail.smtp.starttls.enable}") private val smtpStarttls: Boolean,
) {
    @Bean
    fun javaMailSender(): JavaMailSender {
        val properties = Properties()
        properties.setProperty("mail.smtp.auth", smtpAuth.toString())
        properties.setProperty("mail.smtp.starttls.enable", smtpStarttls.toString())

        val mailSender = JavaMailSenderImpl()
        mailSender.host = host
        mailSender.port = port
        mailSender.username = username
        mailSender.password = password
        mailSender.javaMailProperties = properties
        return mailSender
    }
}
