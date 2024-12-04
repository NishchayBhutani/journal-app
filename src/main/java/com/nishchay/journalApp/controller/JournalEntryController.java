package com.nishchay.journalApp.controller;

import com.nishchay.journalApp.entity.JournalEntry;
import com.nishchay.journalApp.service.JournalEntryService;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal-entries")
public class JournalEntryController {

    private final JournalEntryService journalEntryService;

    public JournalEntryController(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAll() {
        List<JournalEntry> journalEntries = journalEntryService.getAll();
        if(journalEntries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(journalEntries, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> create(@RequestBody JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.save(journalEntry);
        return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalEntry> findById(@PathVariable ObjectId id) {
        Optional<JournalEntry> journalEntryOptional = journalEntryService.findById(id);
        if(journalEntryOptional.isPresent()) {
            return new ResponseEntity<>(journalEntryOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalEntry> update(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry) {
        JournalEntry currEntry = journalEntryService.findById(id).orElse(null);
        if(currEntry != null) {
            currEntry.setTitle(StringUtils.isEmpty(journalEntry.getTitle()) ? currEntry.getTitle() : journalEntry.getTitle());
            currEntry.setContent(StringUtils.isEmpty(journalEntry.getContent()) ? currEntry.getContent() : journalEntry.getContent());
            journalEntryService.save(journalEntry);
            return new ResponseEntity<>(currEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ObjectId id) {
        journalEntryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
