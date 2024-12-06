package com.nishchay.journalApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "journal_entries")
public class JournalEntry {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
}
