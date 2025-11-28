package co.appointment.shared.service;

import co.appointment.grpc.GetUserResponse;
import co.appointment.shared.record.UserRecord;

import java.util.UUID;

public interface GrcpAuthService {
    /**
     * Get User details from Auth Service via gRPC protocol that matches the provided email.
     * @param email user email
     * @return {@link UserRecord}
     */
    UserRecord getUserByEmail(String email);

    /**
     * Get User details from Auth Service via gRPC protocol that matches the provided id.
     * @param id user id
     * @return {@link UserRecord}
     */
    UserRecord getUserById(UUID id);
}
