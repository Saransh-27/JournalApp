package com.company.journalApp.controller;

import com.company.journalApp.api.response.WeatherResponse;
import com.company.journalApp.dto.UserDTO;
import com.company.journalApp.entity.User;
import com.company.journalApp.mapper.UserMapper;
import com.company.journalApp.repositry.UserRepo;
import com.company.journalApp.service.UserService;
import com.company.journalApp.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "User APIs", description = "Read, Update and Delete user")
public class UserController {

    @Autowired
     private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(
                userService.getAll()
                        .stream()
                        .map(UserMapper::toDTO)
                        .toList()
        );
    }



    @PutMapping
    @Operation(summary = "Update user")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Operation(summary = "Delete user")
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/greetings")
    @Operation(summary = "Get greetings + weather")
    public ResponseEntity<?> greetings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Sonipat");
        String greetings = "";
        if (weatherResponse != null && weatherResponse.getCurrent() != null) {
            greetings = " , Weather feels like "
                    + weatherResponse.getCurrent().getFeelslike();
        } else {
            greetings = " , Weather data not available";
        }
        return ResponseEntity.ok("Hi " + authentication.getName() + greetings);
    }

}
