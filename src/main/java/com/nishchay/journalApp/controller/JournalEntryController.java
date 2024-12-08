package com.nishchay.journalApp.controller;

import com.nishchay.journalApp.builder.GetJournalResponseBuilder;
import com.nishchay.journalApp.builder.GetUserResponseBuilder;
import com.nishchay.journalApp.dto.GetJournalResponse;
import com.nishchay.journalApp.entity.JournalEntry;
import com.nishchay.journalApp.service.JournalEntryService;
import com.nishchay.journalApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal-entries")
public class JournalEntryController {

    private final JournalEntryService journalEntryService;
    private final UserService userService;

    public JournalEntryController(JournalEntryService journalEntryService, UserService userService) {
        this.journalEntryService = journalEntryService;
        this.userService = userService;
    }

    @GetMapping("/csrf-token")
    public ResponseEntity<CsrfToken> csrfToken(HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        System.out.println(token);
        if(token != null) {
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<List<GetJournalResponse>> getAllByUsername(HttpServletRequest request, @PathVariable String username) {
//        System.out.println(request.getSession().getId());
//        System.out.println(request.getHeader("Authorization"));
        List<GetJournalResponse> journalEntries = journalEntryService.getAllByUsername(username);
        if(journalEntries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(journalEntries, HttpStatus.OK);
    }

    @PostMapping("/users/{username}")
    public ResponseEntity<GetJournalResponse> create(@RequestBody JournalEntry journalEntry, @PathVariable String username) {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.save(journalEntry, username);
        return new ResponseEntity<>(GetJournalResponseBuilder.build(journalEntry), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetJournalResponse> findById(@PathVariable Long id) {
        Optional<JournalEntry> journalEntryOptional = journalEntryService.findById(id);
        if(journalEntryOptional.isPresent()) {
            return new ResponseEntity<>(GetJournalResponseBuilder.build(journalEntryOptional.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetJournalResponse> update(@PathVariable Long id, @RequestBody JournalEntry journalEntry) {
        return new ResponseEntity<>(journalEntryService.update(id, journalEntry), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        journalEntryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
