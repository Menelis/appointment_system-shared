package co.appointment.shared.util;

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
     * @param topic Kafka topic
     * @param key Event Key
     * @param event Event Value
     * @param eventHeaders Event Headers
     * @param <K> Generic Key
     * @param <V> Generic Value
     */
    public  static <K, V> void sendKafkaEvent(
            final KafkaTemplate<K, V> kafkaTemplate,
            final String topic,
            final K key,
            final V event,
            final Map<String, Object> eventHeaders) {
       CompletableFuture<SendResult<K, V>> completableFuture =
               kafkaTemplate.send(new ProducerRecord<>(
                       topic,null,System.currentTimeMillis(),key,event, getEventHeaders(eventHeaders)));
       completableFuture.whenComplete((result, error) -> {
           if (error != null) {
               log.error(error.getMessage(), error);
               return;
           }
           RecordMetadata recordMetadata = result.getRecordMetadata();
           log.info("Event was sent successfully to topic: {}, partition: {}, offset: {} at timestamp: {}",
                   topic, recordMetadata.partition(), recordMetadata.offset(), recordMetadata.timestamp());
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
