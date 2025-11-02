package co.appointment.shared.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Base entity for field created_At.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    /**
     * Audit fields of when record when created.
     */
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
//    /**
//     * Audit fields of who created the record.
//     */
//    @Column(nullable = false, name = "created_by")
//    private UUID createdBy;
}
