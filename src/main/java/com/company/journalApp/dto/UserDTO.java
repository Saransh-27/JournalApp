package com.company.journalApp.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String userName;
    private String email;
    private boolean sentimentAnalysis;
    private List<String> roles;

    // Only for request (signup / update)
    private String password;
}
