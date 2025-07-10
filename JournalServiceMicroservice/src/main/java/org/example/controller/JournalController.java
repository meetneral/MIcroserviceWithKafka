package org.example.controller;



import org.example.model.JournalEntryEvent;
import org.example.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JournalController {

    @Autowired
    private JournalService journalService;

    @PostMapping("/journal/create")
    public ResponseEntity<String> createJournal(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("userId") String userId) {
        try {
            JournalEntryEvent event = journalService.createJournalEntry(title, content, userId);
            return ResponseEntity.ok("Journal entry created and event published: " + event.getEventId());
        } catch (Exception e) {
            System.err.println("Error creating journal entry: " + e.getMessage());
            return ResponseEntity.status(500).body("Error creating journal entry: " + e.getMessage());
        }
    }
}
