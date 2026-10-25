package co.appointment.shared.service;

import co.appointment.shared.record.BranchSummary;

public interface GrcpBranchService {

    /**
     * Get Branch details from Branch Service via gRPC protocol.
     *
     * @param id branch id
     * @return {@link BranchSummary}
     */
    BranchSummary getBranchById(int id);
}
