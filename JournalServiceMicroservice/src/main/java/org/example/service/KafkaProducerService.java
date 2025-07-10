package org.example.service;

import org.example.model.JournalEntryEvent; // Import your event class
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, JournalEntryEvent> kafkaTemplate; // Changed Object to JournalEntryEvent

    public void publishJournalEvent(String key, JournalEntryEvent event) {
        System.out.println(String.format("JournalProducer: Publishing event to topic %s: key=%s, event=%s", topic, key, event));
        kafkaTemplate.send(topic, key, event);
    }
}