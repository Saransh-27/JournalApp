package com.company.journalApp.service;

import com.company.journalApp.entity.User;
import com.company.journalApp.repositry.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@Disabled("Temporarily disabled – DB config pending")
@SpringBootTest
public class UserDetailsServiceImplTests {
    @Autowired
    private UserDetailsServiceImpl userDetailService;

    @Mock
    private UserRepo userRepo;

    @Test
    void loadUserByUsernameTest(){
        when(userRepo.findByUserName("saransh")).thenReturn(User.builder().userName("saransh").password("saransh").roles(new ArrayList<>()).build());
        UserDetails user = userDetailService.loadUserByUsername("saransh");
        assertNotNull(user);
    }

}
