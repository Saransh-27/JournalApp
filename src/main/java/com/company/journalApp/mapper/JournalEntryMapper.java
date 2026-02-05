package com.company.journalApp.mapper;

import com.company.journalApp.dto.JournalEntryDTO;
import com.company.journalApp.entity.JournalEntry;

public class JournalEntryMapper {

    private JournalEntryMapper() {}

    public static JournalEntryDTO toDTO(JournalEntry entry) {
        return JournalEntryDTO.builder()
                .title(entry.getTitle())
                .content(entry.getContent())
                .sentiment(entry.getSentiment())
                .build();
    }

    public static JournalEntry toEntity(JournalEntryDTO dto) {
        JournalEntry entry = new JournalEntry();
        entry.setTitle(dto.getTitle());
        entry.setContent(dto.getContent());
        entry.setSentiment(dto.getSentiment());
        return entry;
    }
}
