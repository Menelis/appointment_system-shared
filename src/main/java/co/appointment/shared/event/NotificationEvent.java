package co.appointment.shared.event;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Notification Event. The event can be sent to any messaging service
 *
 * @param <T>
 */
@Getter
@SuperBuilder
public abstract class NotificationEvent<T> {
    private final T event;
}
