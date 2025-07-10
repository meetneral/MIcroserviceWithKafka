package org.example.model;

// In journal-service/src/main/java/com/example/journal/model/JournalEntryEvent.java
// In notification-service/src/main/java/com/example/notification/model/JournalEntryEvent.java

 // Adjust package for notification-service

import java.time.Instant; // For timestamp

public class JournalEntryEvent {
    private String eventId;
    private String entryId;
    private String title;
    private String userId; // Who created the entry
    private String eventType; // e.g., "CREATED", "UPDATED", "DELETED"
    private Instant timestamp;

    public JournalEntryEvent() {
        this.eventId = java.util.UUID.randomUUID().toString(); // Generate unique ID for each event
        this.timestamp = Instant.now();
    }

    // Constructor for convenience (without eventId/timestamp)
    public JournalEntryEvent(String entryId, String title, String userId, String eventType) {
        this(); // Call default constructor to set eventId and timestamp
        this.entryId = entryId;
        this.title = title;
        this.userId = userId;
        this.eventType = eventType;
    }

    // Getters and Setters (REQUIRED for JSON serialization)
    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }
    public String getEntryId() { return entryId; }
    public void setEntryId(String entryId) { this.entryId = entryId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "JournalEntryEvent{" +
                "eventId='" + eventId + '\'' +
                ", entryId='" + entryId + '\'' +
                ", title='" + title + '\'' +
                ", userId='" + userId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
