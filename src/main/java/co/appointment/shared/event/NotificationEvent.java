package co.appointment.shared.event;


import lombok.Getter;

/**
 * Notification Event.
 * The event can be sent to any messaging service
 * @param <T>
 */
@Getter
public abstract class NotificationEvent<T> {
   private final T event;
   public NotificationEvent(final T event) {
       this.event = event;
   }
}

