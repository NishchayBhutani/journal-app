package com.nishchay.journalApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class GetJournalResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime date;
    private Long userId;
}
