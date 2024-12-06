package com.nishchay.journalApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @Column(unique = true)
    private String username;
    @NonNull
    private String password;
    @OneToMany
    private List<JournalEntry> journalEntries = new ArrayList<>();
}
