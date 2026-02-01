package com.company.journalApp.mapper;

import com.company.journalApp.dto.UserDTO;
import com.company.journalApp.entity.User;

public class UserMapper {

    private UserMapper() {} // prevent instantiation

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId().toString())
                .userName(user.getUserName())
                .email(user.getEmail())
                .sentimentAnalysis(user.isSentimentAnalysis())
                .roles(user.getRoles())
                .build();
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        return user;
    }
}
