package co.appointment.shared.event;

public record EmailEvent(
        String recipientEmail, String subject, String body, boolean bodyEncrypted, boolean recipientEmailEncrypted) {}
