package com.nishchay.journalApp.builder;

import com.nishchay.journalApp.dto.GetJournalResponse;
import com.nishchay.journalApp.entity.JournalEntry;

public class GetJournalResponseBuilder {

    public static GetJournalResponse build(JournalEntry entry) {
        return GetJournalResponse
                .builder()
                .id(entry.getId())
                .title(entry.getTitle())
                .content(entry.getContent())
                .date(entry.getDate())
                .userId(entry.getUser().getId())
                .build();
    }
}