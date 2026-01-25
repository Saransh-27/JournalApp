package com.company.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@Disabled("Only used for check the redis connection")
@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    void testRedis(){
        redisTemplate.opsForValue().set("email", "saransh@gmail.com");
        Object salary = redisTemplate.opsForValue().get("salary");
        int a = 2;
    }
}
