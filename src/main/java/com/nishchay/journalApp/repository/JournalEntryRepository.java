package com.nishchay.journalApp.repository;

import com.nishchay.journalApp.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
}
