package org.example.config;



import org.example.model.JournalEntryEvent; // Import your shared event model
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // --- Producer Configuration ---

    @Bean
    public ProducerFactory<String, JournalEntryEvent> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // Use JsonSerializer for your custom JournalEntryEvent objects
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // Optional: Ensure type headers are NOT added for simpler deserialization on the consumer side
        // This is good for interoperability if consumers are not all Spring Kafka Java clients.
        // If your consumer is also Spring Kafka, you can leverage type headers, but it adds complexity.
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

        // Optional: Producer reliability and performance settings
        // configProps.put(ProducerConfig.ACKS_CONFIG, "all"); // Require all in-sync replicas to acknowledge
        // configProps.put(ProducerConfig.RETRIES_CONFIG, 3); // Number of retries on failed sends
        // configProps.put(ProducerConfig.LINGER_MS_CONFIG, 1); // Batch messages for 1ms
        // configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384); // 16KB batch size

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, JournalEntryEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}