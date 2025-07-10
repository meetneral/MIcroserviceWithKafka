package org.example.consumer;



import org.example.model.JournalEntryEvent; // Import the shared event class
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class JournalEventConsumer {

    @KafkaListener(topics = "journal.events", groupId = "notification-service-group", containerFactory = "kafkaListenerContainerFactory")
    public void listenJournalEvents(JournalEntryEvent event) {
        System.out.println("NotificationService: Received Journal Event: " + event);

        // Simulate sending a notification (e.g., email, push notification, internal alert)
        if ("CREATED".equals(event.getEventType())) {
            System.out.println(String.format("NotificationService: Sending notification for new journal entry '%s' by user '%s'.",
                    event.getTitle(), event.getUserId()));
            // Add your notification logic here (e.g., call an email service)
        } else if ("UPDATED".equals(event.getEventType())) {
            System.out.println(String.format("NotificationService: Journal entry '%s' by user '%s' was updated.",
                    event.getTitle(), event.getUserId()));
        }
        // ... handle other event types
    }
}