package co.appointment.shared.event.kafka;

import co.appointment.shared.event.NotificationEvent;
import lombok.Getter;

import java.util.Map;

/**
 * Kafka Notification Event.
 * @param <K> Generic type for message key
 * @param <V> Generic type for message value
 */
@Getter
public class KafkaNotificationEvent<K, V> extends NotificationEvent<V> {
    private final String topic;
    private final Map<String, Object> eventHeaders;
    private final K key;

    public KafkaNotificationEvent(
            final String topic, final V event, final Map<String, Object> eventHeaders, final K key) {
        super(event);
        this.topic = topic;
        this.eventHeaders = eventHeaders;
        this.key = key;
    }
}
