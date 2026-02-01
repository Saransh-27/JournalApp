package com.company.journalApp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupDTO {
    private String userName;
    private String password;
    private String email;
    private String sentimentAnalysis;
}
