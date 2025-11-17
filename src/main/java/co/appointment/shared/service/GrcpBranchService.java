package co.appointment.shared.service;

import co.appointment.grpc.GetBranchResponse;

public interface GrcpBranchService {

    GetBranchResponse getBranchById(int id);
}
