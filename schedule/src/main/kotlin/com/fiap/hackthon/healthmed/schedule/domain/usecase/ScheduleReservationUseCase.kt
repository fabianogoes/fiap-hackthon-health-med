package com.fiap.hackthon.healthmed.schedule.domain.usecase

import com.fiap.hackthon.healthmed.doctor.domain.entity.Doctor
import com.fiap.hackthon.healthmed.doctor.domain.exception.DoctorNotFoundException
import com.fiap.hackthon.healthmed.doctor.ports.DoctorPersistencePort
import com.fiap.hackthon.healthmed.patient.domain.entity.Patient
import com.fiap.hackthon.healthmed.patient.domain.exception.PatientNotFoundException
import com.fiap.hackthon.healthmed.patient.ports.PatientPersistencePort
import com.fiap.hackthon.healthmed.schedule.domain.entity.Schedule
import com.fiap.hackthon.healthmed.schedule.domain.exception.ScheduleAlreadyReservedException
import com.fiap.hackthon.healthmed.schedule.domain.exception.ScheduleNotFoundException
import com.fiap.hackthon.healthmed.schedule.ports.SchedulePersistencePort
import com.fiap.hackthon.healthmed.schedule.ports.ScheduleReservationPort
import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.shared.SharedDateUtils.toStringFormatted
import com.fiap.hackthon.healthmed.shared.logger
import com.fiap.hackthon.healthmed.shared.ports.MailPort
import jakarta.inject.Named
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Named
open class ScheduleReservationUseCase(
    private val schedulePersistencePort: SchedulePersistencePort,
    private val doctorPersistencePort: DoctorPersistencePort,
    private val patientPersistencePort: PatientPersistencePort,
    private val mailPort: MailPort,
) : ScheduleReservationPort {
    private val log = logger<ScheduleReservationUseCase>()

    @Transactional
    override fun reserve(
        id: UUID,
        patientEmail: Email,
    ) {
        val schedule =
            schedulePersistencePort
                .readById(id)
                ?: throw ScheduleNotFoundException(id.toString())

        if (!schedule.canBeReserved()) throw ScheduleAlreadyReservedException(schedule)

        val doctor =
            doctorPersistencePort
                .readOneByEmail(schedule.slot.doctorEmail)
                ?: throw DoctorNotFoundException(schedule.slot.doctorEmail.value)

        val patient =
            patientPersistencePort
                .readOneByEmail(patientEmail)
                ?: throw PatientNotFoundException(patientEmail.value)

        schedulePersistencePort
            .save(schedule.copy(patientEmail = patientEmail).reserved())
            .also { sendMail(schedule, doctor, patient) }
    }

    private fun sendMail(
        schedule: Schedule,
        doctor: Doctor,
        patient: Patient,
    ) {
        kotlin.runCatching {
            log.info("sending email to doctor {} for reservation schedule {} to patient {}", doctor.email, schedule, patient.email)

            val messageVars =
                mapOf(
                    MAIL_SCHEDULE_DOCTOR_VARIABLE_NAME to doctor.name,
                    MAIL_SCHEDULE_PATIENT_VARIABLE_NAME to patient.name,
                    MAIL_SCHEDULE_DATE_VARIABLE_NAME to schedule.slot.date.toStringFormatted(),
                    MAIL_SCHEDULE_TIME_VARIABLE_NAME to "${schedule.slot.startTime}",
                )

            mailPort.sendEmail(
                to = schedule.slot.doctorEmail.value,
                subject = MAIL_SUBJECT,
                messageVars = messageVars,
                templateName = MAIL_TEMPLATE,
            )
        }.onSuccess {
            log.info("email sent to doctor: ${doctor.name} with patient: ${patient.name} successfully")
        }.onFailure {
            schedulePersistencePort
                .save(schedule.mailError())
                .also { log.error("error to send email to doctor: ${doctor.name} with patient: ${patient.name}", it) }
        }
    }

    companion object {
        private const val MAIL_TEMPLATE = "schedule-mail-template.html"
        private const val MAIL_SUBJECT = "Health&Med - Nova consulta agendada"
        private const val MAIL_SCHEDULE_DOCTOR_VARIABLE_NAME = "doctor_name"
        private const val MAIL_SCHEDULE_PATIENT_VARIABLE_NAME = "patient_name"
        private const val MAIL_SCHEDULE_DATE_VARIABLE_NAME = "schedule_date"
        private const val MAIL_SCHEDULE_TIME_VARIABLE_NAME = "schedule_time"
    }
}
