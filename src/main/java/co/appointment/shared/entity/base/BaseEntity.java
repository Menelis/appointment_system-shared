package co.appointment.shared.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/** Base entity for field created_At. */
@Data
@MappedSuperclass
@SuperBuilder
public abstract class BaseEntity {
    /** Audit fields of when record when created. */
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
