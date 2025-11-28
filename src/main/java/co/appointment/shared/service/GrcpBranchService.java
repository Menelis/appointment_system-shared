package co.appointment.shared.service;

import co.appointment.shared.record.BranchRecord;

public interface GrcpBranchService {

    /**
     * Get Branch details from Branch Service via gRPC protocol.
     * @param id branch id
     * @return {@link BranchRecord}
     */
    BranchRecord getBranchById(int id);
}
