package com.fiap.hackthon.healthmed.shared.adapters

import com.fiap.hackthon.healthmed.shared.ports.MailPort
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

@Service
class MailAdapter(
    private val javaMailSender: JavaMailSender,
    private val templateEngine: TemplateEngine,
) : MailPort {

    @Async
    override fun sendEmail(to: String, subject: String, messageVars: Map<String, String>, templateName: String) {
        val message = MimeMessageHelper(
            javaMailSender.createMimeMessage(),
            MAIL_MULTIPART,
            MAIL_ENCODING,
        )

        message.setTo(to)
        message.setSubject(subject)

        val context = Context()
        context.setVariables(messageVars)
        val htmlContent = templateEngine.process(templateName, context)

        message.setText(htmlContent, MAIL_HTML)

        javaMailSender.send(message.mimeMessage)
    }

    companion object {
        private const val MAIL_ENCODING = "UTF-8"
        private const val MAIL_MULTIPART = true
        private const val MAIL_HTML = true
    }

}