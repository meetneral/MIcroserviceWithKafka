package org.example.service;

// package net.engineeringdigest.journalApp.service; (Adjust package)

import org.example.model.JournalEntryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JournalService {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    // Simulates saving a journal entry and then publishing an event
    public JournalEntryEvent createJournalEntry(String title, String content, String userId) {
        String entryId = UUID.randomUUID().toString();
        System.out.println("JournalService: Creating new journal entry: " + title);
        System.out.println("JournalService: Creating new journal entry: " + content);
        // In a real app, you'd save to a database here
        // ...

        // Create and publish the event
        JournalEntryEvent event = new JournalEntryEvent(entryId, title, userId, "CREATED");
        kafkaProducerService.publishJournalEvent(entryId, event); // Use entryId as key for ordering
        return event;
    }
}