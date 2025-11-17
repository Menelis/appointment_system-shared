package co.appointment.shared.service;

import co.appointment.grpc.BranchServiceGrpc;
import co.appointment.grpc.GetBranchByIdRequest;
import co.appointment.grpc.GetBranchResponse;

public abstract class GrcpBranchServiceBase {

    protected final BranchServiceGrpc.BranchServiceBlockingStub branchServiceBlockingStub;

    public GrcpBranchServiceBase(final BranchServiceGrpc.BranchServiceBlockingStub branchServiceBlockingStub) {
        this.branchServiceBlockingStub = branchServiceBlockingStub;
    }

    public GetBranchResponse getBranchById(final int id) {
        GetBranchByIdRequest request = GetBranchByIdRequest.newBuilder()
                .setId(id)
                .build();
        return branchServiceBlockingStub.processBranchById(request);
    }
}
