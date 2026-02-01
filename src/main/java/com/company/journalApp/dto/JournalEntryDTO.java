package com.company.journalApp.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JournalEntryDTO {
    private String title;
    private String content;
}
