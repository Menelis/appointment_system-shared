package co.appointment.shared.event.kafka;

import co.appointment.shared.event.NotificationEvent;
import java.util.Map;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Kafka Notification Event.
 *
 * @param <K> Generic type for message key
 * @param <V> Generic type for message value
 */
@Getter
@SuperBuilder
public class KafkaNotificationEvent<K, V> extends NotificationEvent<V> {
    private final String topic;
    private final Map<String, Object> eventHeaders;
    private final K key;
}
