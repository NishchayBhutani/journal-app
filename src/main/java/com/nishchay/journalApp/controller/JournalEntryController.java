package com.nishchay.journalApp.controller;

import com.nishchay.journalApp.entity.JournalEntry;
import com.nishchay.journalApp.service.JournalEntryService;
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
    public ResponseEntity<JournalEntry> findById(@PathVariable Integer id) {
        Optional<JournalEntry> journalEntryOptional = journalEntryService.findById(id);
        if(journalEntryOptional.isPresent()) {
            return new ResponseEntity<>(journalEntryOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalEntry> update(@PathVariable Integer id, @RequestBody JournalEntry journalEntry) {
        return new ResponseEntity<>(journalEntryService.update(id, journalEntry), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        journalEntryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
