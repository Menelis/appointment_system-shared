package co.appointment.shared.event;

public record EmailEvent(
        String recipientEmail, String subject, String body, boolean bodyEncrypted, boolean recipientEmailEncrypted) {
    public EmailEvent(final String recipientEmail, final String subject, final String body) {
        this(recipientEmail, subject, body, false, false);
    }
}
