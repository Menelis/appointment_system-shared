package co.appointment.shared.record;
import co.appointment.grpc.GetBranchResponse;
/**
 * A record to map {@link GetBranchResponse} to serializable object.
 */
public record BranchRecord(
        String name,
        String streetNo,
        String addressLine1,
        String addressLine2,
        String city,
        String province,
        String postalCode,
        String email,
        String landLine,
        String faxNo) {}

