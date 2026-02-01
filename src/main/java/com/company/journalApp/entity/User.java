package com.company.journalApp.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Schema(hidden = true)
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @Schema(
            description = "User email address",
            example = "user@gmail.com",
            hidden = true
    )
    private String email;
    @Schema(hidden = true)
    private boolean sentimentAnalysis;
    @NonNull
    private String password;
    @DBRef
    @Builder.Default
    @Schema(hidden = true)
    private List<JournalEntry> journalEntries = new ArrayList<>();
    @Builder.Default
    @Schema(hidden = true)
    private List<String> roles = new ArrayList<>();
}
