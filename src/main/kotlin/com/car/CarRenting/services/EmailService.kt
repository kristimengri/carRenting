package com.car.CarRenting.services

import com.car.CarRenting.enums.EmailTemplateName
import jakarta.mail.MessagingException
import jakarta.mail.internet.MimeMessage
import lombok.RequiredArgsConstructor
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine
import java.nio.charset.StandardCharsets.UTF_8
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.*

@Service
@RequiredArgsConstructor
class EmailService(
    private val mailSender: JavaMailSenderImpl,
    private val templateEngine: SpringTemplateEngine
) {


    @Async
    @Throws(MessagingException::class)
    fun sendEmail(
        to: String,
        username: String,
        emailTemplate: EmailTemplateName,
        confirmationUrl: String,
        activationCode: String,
        subject: String

    ) {
        val templateName: String = emailTemplate.name

        val mimeMessage: MimeMessage = mailSender.createMimeMessage()
        val helper: MimeMessageHelper = MimeMessageHelper(
            mimeMessage,
            MULTIPART_MODE_MIXED,
            UTF_8.name()
        )

        val properties = HashMap<String, Any>().apply {
            put("username", username)
            put("confirmationUrl", confirmationUrl)
            put("activation_code", activationCode)
        }

        val context = Context().apply {
            setVariables(properties)
        }

        helper.setFrom("kent@gmail.com")
        helper.setTo(to)
        helper.setSubject(subject)

        val template: String = templateEngine.process(templateName, context)
        helper.setText(template, true)

        mailSender.send(mimeMessage)

    }


}