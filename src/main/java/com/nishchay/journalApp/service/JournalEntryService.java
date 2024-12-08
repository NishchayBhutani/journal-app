package com.nishchay.journalApp.service;

import com.nishchay.journalApp.builder.GetJournalResponseBuilder;
import com.nishchay.journalApp.dto.GetJournalResponse;
import com.nishchay.journalApp.entity.JournalEntry;
import com.nishchay.journalApp.entity.User;
import com.nishchay.journalApp.repository.JournalEntryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.nishchay.journalApp.util.Constants.USER_NOT_FOUND;

@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final UserService userService;

    JournalEntryService(JournalEntryRepository journalEntryRepository, UserService userService) {
        this.journalEntryRepository = journalEntryRepository;
        this.userService = userService;
    }

    @Transactional
    public void save(JournalEntry journalEntry, String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));
        user.getJournalEntries().add(journalEntry);
        journalEntry.setUser(user);
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public List<GetJournalResponse> getAllByUsername(String username) {
        Optional<User> userOptional = userService.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));
        return user.getJournalEntries().stream().map(GetJournalResponseBuilder::build).toList();
    }

    public Optional<JournalEntry> findById(Long id) {
        return journalEntryRepository.findById(id);
    }

    public GetJournalResponse update(Long id, JournalEntry journalEntry) {
        Optional<JournalEntry> journalEntryOptional = journalEntryRepository.findById(id);
        if(journalEntryOptional.isPresent()) {
            JournalEntry currEntry = journalEntryOptional.get();
            currEntry.setTitle(journalEntry.getTitle());
            currEntry.setContent(journalEntry.getContent());
            return GetJournalResponseBuilder.build(journalEntryRepository.save(currEntry));
        }
        return null;
    }

    public void delete(Long id) {
        journalEntryRepository.deleteById(id);
    }
}
