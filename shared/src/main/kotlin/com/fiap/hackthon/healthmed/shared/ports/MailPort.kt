package com.fiap.hackthon.healthmed.shared.ports

interface MailPort {
    fun sendEmail(
        to: String,
        subject: String,
        messageVars: Map<String, String>,
        templateName: String,
    )
}
