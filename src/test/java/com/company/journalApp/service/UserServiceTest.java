package com.company.journalApp.service;

import com.company.journalApp.repositry.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;


    @Disabled("Temporarily disabled – sample test example ")
    @ParameterizedTest
    @CsvSource({"saransh", "monu", "aryan"})
    public void test(String name){
        assertNotNull(userRepo.findByUserName(name), "failed for :" + name);

    }

    @Disabled("Temporarily disabled – sample test example ")
    @ParameterizedTest
    @CsvSource({"1,2,3","4,5,6","7,8,9"})
    public void usertest(int a, int b, int accpected){
       assertEquals(accpected, a+b, "failed for :" + a + "," + b + "," + accpected);
    }

}
