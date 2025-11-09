package co.appointment.shared.service;

import co.appointment.grpc.GetUserResponse;

import java.util.UUID;

public interface GrcpAuthService {
    GetUserResponse getUserByEmail(String email);
    GetUserResponse getUserById(UUID id);
}
