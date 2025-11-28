package co.appointment.shared.service;

import co.appointment.grpc.BranchServiceGrpc;
import co.appointment.grpc.GetBranchByIdRequest;
import co.appointment.grpc.GetBranchResponse;
import co.appointment.shared.record.BranchRecord;

public abstract class GrcpBranchServiceBase {

    protected final BranchServiceGrpc.BranchServiceBlockingStub branchServiceBlockingStub;

    public GrcpBranchServiceBase(final BranchServiceGrpc.BranchServiceBlockingStub branchServiceBlockingStub) {
        this.branchServiceBlockingStub = branchServiceBlockingStub;
    }

    public BranchRecord getBranchById(final int id) {
        GetBranchByIdRequest request = GetBranchByIdRequest.newBuilder()
                .setId(id)
                .build();
        GetBranchResponse response = branchServiceBlockingStub.processBranchById(request);
        if(response == null) {
            return null;
        }
        return new BranchRecord(
                response.getName(), response.getStreetNo(), response.getAddressLine1(), response.getAddressLine2(), response.getCity(),
                response.getProvince(), response.getPostalCode(), response.getEmailAddress(), response.getLandLine(), response.getFaxNumber());
    }
}
