package com.nishchay.journalApp.service;

import com.nishchay.journalApp.entity.JournalEntry;
import com.nishchay.journalApp.repository.JournalEntryRepository;
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

    public Optional<JournalEntry> findById(Integer id) {
        return journalEntryRepository.findById(id);
    }

    public JournalEntry update(Integer id, JournalEntry journalEntry) {
        Optional<JournalEntry> journalEntryOptional = journalEntryRepository.findById(id);
        if(journalEntryOptional.isPresent()) {
            JournalEntry currEntry = journalEntryOptional.get();
            currEntry.setTitle(journalEntry.getTitle());
            currEntry.setContent(journalEntry.getContent());
            return journalEntryRepository.save(currEntry);
        }
        return null;
    }

    public void delete(Integer id) {
        journalEntryRepository.deleteById(id);
    }
}
