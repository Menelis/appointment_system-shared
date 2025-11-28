package co.appointment.shared.record;

import co.appointment.grpc.GetUserResponse;

import java.util.UUID;

/**
 * A record to map {@link GetUserResponse} to serializable object.
 */
public record UserRecord(UUID id, String fullName, String email) {}
