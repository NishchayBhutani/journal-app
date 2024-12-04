package com.nishchay.journalApp.service;

import com.nishchay.journalApp.entity.JournalEntry;
import com.nishchay.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;

    JournalEntryService(JournalEntryRepository journalEntryRepository) {
        this.journalEntryRepository = journalEntryRepository;
    }

    public void save(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public JournalEntry update(ObjectId id, JournalEntry journalEntry) {
        return journalEntryRepository.save(journalEntry);
    }

    public void delete(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }
}
