package co.appointment.shared.kafka.event;

public record EmailEvent(String recipientEmail, String subject, String body) {}
