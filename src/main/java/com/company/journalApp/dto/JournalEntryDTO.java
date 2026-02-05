package com.company.journalApp.dto;

import com.company.journalApp.enums.Sentiment;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JournalEntryDTO {
    private String title;
    private String content;
    private Sentiment sentiment;
}
