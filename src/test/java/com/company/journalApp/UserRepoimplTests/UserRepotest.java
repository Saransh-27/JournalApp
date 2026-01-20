package com.company.journalApp.UserRepoimplTests;

import com.company.journalApp.entity.User;
import com.company.journalApp.repositry.UserRepoimpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepotest {

    @Autowired
    private UserRepoimpl userRepoimpl;

    @Test
    @Disabled
    public void testGetUserforSA(){
        userRepoimpl.getUserforSA();

    }
}
