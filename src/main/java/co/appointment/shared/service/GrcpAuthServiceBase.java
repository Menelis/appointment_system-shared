package co.appointment.shared.service;

import co.appointment.grpc.AuthServiceGrpc;
import co.appointment.grpc.GetUserByEmailRequest;
import co.appointment.grpc.GetUserByIdRequest;
import co.appointment.grpc.GetUserResponse;

import java.util.UUID;


public abstract class GrcpAuthServiceBase {

    protected final AuthServiceGrpc.AuthServiceBlockingStub authServiceBlockingStub;

    public GrcpAuthServiceBase(final AuthServiceGrpc.AuthServiceBlockingStub authServiceBlockingStub) {
        this.authServiceBlockingStub = authServiceBlockingStub;
    }
    public GetUserResponse getUserByEmail(final String email) {
        GetUserByEmailRequest request = GetUserByEmailRequest.newBuilder()
                .setEmail(email)
                .build();
        return authServiceBlockingStub.processUserByEmail(request);
    }
    public GetUserResponse getUserById(final UUID id) {
        GetUserByIdRequest request = GetUserByIdRequest.newBuilder()
                .setId(id.toString())
                .build();
        return authServiceBlockingStub.processUserById(request);
    }
}
