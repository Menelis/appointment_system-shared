package co.appointment.shared.util;

import co.appointment.shared.event.kafka.KafkaNotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class KafkaUtils {
    /**
     * Send Kafka Event.
     * @param kafkaTemplate {@link KafkaTemplate} instance
     * @param kafkaNotificationEvent {@link KafkaNotificationEvent}
     * @param <K> generic Key
     * @param <V> Generic value
     */
    public static <K, V> void sendKafkaEvent(
            final KafkaTemplate<K, V> kafkaTemplate,
            KafkaNotificationEvent<K , V> kafkaNotificationEvent) {
        CompletableFuture<SendResult<K, V>> completableFuture =
                kafkaTemplate.send(new ProducerRecord<>(
                        kafkaNotificationEvent.getTopic(), null, System.currentTimeMillis(), kafkaNotificationEvent.getKey(), kafkaNotificationEvent.getEvent(), getEventHeaders(kafkaNotificationEvent.getEventHeaders())));
        completableFuture.whenComplete((result, error) -> {
            if(error != null) {
                log.error("There was an issue sending an event to kafka topic:{} with message: {}", kafkaNotificationEvent.getTopic(), error.getMessage());
                return;
            }
            RecordMetadata recordMetadata = result.getRecordMetadata();
            log.info("Successfully sent message=[{}] to topic partition:{}-{} with offset:{} at timestamp:{}",
                    kafkaNotificationEvent.getEvent(), recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(), recordMetadata.timestamp());
        });
    }
    private static List<Header> getEventHeaders(final  Map<String, Object> eventHeaders) {
        final List<Header> headers = new ArrayList<>();
        if(eventHeaders.isEmpty()) {
            return headers;
        }
        eventHeaders.forEach((key, value) -> headers.add(new RecordHeader(key, value.toString().getBytes(StandardCharsets.UTF_8))));

        return headers;
    }
}
