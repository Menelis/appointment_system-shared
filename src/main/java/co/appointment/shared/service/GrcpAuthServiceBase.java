package co.appointment.shared.service;

import co.appointment.grpc.AuthServiceGrpc;
import co.appointment.grpc.GetUserByEmailRequest;
import co.appointment.grpc.GetUserByIdRequest;
import co.appointment.grpc.GetUserResponse;
import co.appointment.shared.record.UserRecord;

import java.util.UUID;


public abstract class GrcpAuthServiceBase {

    protected final AuthServiceGrpc.AuthServiceBlockingStub authServiceBlockingStub;

    public GrcpAuthServiceBase(final AuthServiceGrpc.AuthServiceBlockingStub authServiceBlockingStub) {
        this.authServiceBlockingStub = authServiceBlockingStub;
    }
    public UserRecord getUserByEmail(final String email) {
        GetUserByEmailRequest request = GetUserByEmailRequest.newBuilder()
                .setEmail(email)
                .build();
        return getUserRecord(authServiceBlockingStub.processUserByEmail(request));
    }
    public UserRecord getUserById(final UUID id) {
        GetUserByIdRequest request = GetUserByIdRequest.newBuilder()
                .setId(id.toString())
                .build();
        return getUserRecord(authServiceBlockingStub.processUserById(request));
    }
    private UserRecord getUserRecord(final GetUserResponse response) {
        if(response == null) {
            return null;
        }
        return new UserRecord(UUID.fromString(response.getId()), response.getFullName(), response.getEmail());
    }
}
