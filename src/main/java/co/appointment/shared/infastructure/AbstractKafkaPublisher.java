package co.appointment.shared.infastructure;

import static co.appointment.shared.constant.EventTypeConstants.EVENT_TYPE;
import static co.appointment.shared.constant.SharedConstants.X_CORRELATION_ID;

import co.appointment.shared.event.DomainEvent;
import co.appointment.shared.event.DomainEventMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@RequiredArgsConstructor
@Slf4j
public abstract class AbstractKafkaPublisher implements KafkaPublisher {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void publish(
            final String topic,
            final DomainEventMessage<? extends DomainEvent> eventMessage,
            final Map<String, Object> headers) {
        try {
            Message<byte[]> message = MessageBuilder.withPayload(
                            objectMapper.writeValueAsBytes(eventMessage.getEvent()))
                    .setHeader(KafkaHeaders.TOPIC, topic)
                    .setHeader(KafkaHeaders.KEY, eventMessage.getMessageKey())
                    .setHeader(X_CORRELATION_ID, eventMessage.getCorrelationId())
                    .setHeader(EVENT_TYPE, eventMessage.getEventType())
                    .copyHeadersIfAbsent(headers)
                    .build();

            kafkaTemplate.send(message);

        } catch (JsonProcessingException jsonProcessingException) {
            log.error(
                    "There was an error serializing object to bytes[] with error message=[{}]",
                    jsonProcessingException.getMessage(),
                    jsonProcessingException);

            throw new RuntimeException(jsonProcessingException);

        } catch (Exception exception) {

            log.error(exception.getMessage(), exception);

            throw new RuntimeException("Error publishing Kafka message to topic=[%s]".formatted(topic), exception);
        }
    }
}
