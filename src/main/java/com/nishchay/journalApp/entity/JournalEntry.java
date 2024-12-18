package com.nishchay.journalApp.entity;

import jakarta.persistence.*;
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
    @Column(name = "journal_id")
    private Long id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
    private User user;
}
